package com.system.usecase.saleusecase;

import com.system.model.client.gateways.ClientRepository;
import com.system.model.enums.ClientErrorEnum;
import com.system.model.enums.GenericErrorEnum;
import com.system.model.enums.ProductErrorEnum;
import com.system.model.exceptions.ClientException;
import com.system.model.exceptions.GenericException;
import com.system.model.exceptions.ProductException;
import com.system.model.product.Product;
import com.system.model.product.gateways.ProductRepository;
import com.system.model.sale.Sale;
import com.system.model.sale.gateways.SaleRepository;
import com.system.model.saledetail.SaleDetail;
import com.system.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.system.model.utils.DateFunctions.getActualTime;
import static com.system.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class SaleCommandUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    Logger log = Logger.getLogger(SaleCommandUseCase.class.getName());

    private static final int ZERO = 0;

    public Mono<Sale> saveSale(Sale sale) {
        log.info("*** ENTER TO SaleCommandUseCase :: saveSale - clientId: " + sale.getClientId());

        Mono<Map<String, Product>> productListMap = Mono.just(sale)
                .filter(saleDTO -> Objects.nonNull(sale.getSaleDetails()))
                .flatMap(saleDTO -> Flux.fromIterable(sale.getSaleDetails())
                        .flatMap(saleDetail -> productRepository.findByProductId(saleDetail.getProductId()))
                        .collectMap(Product::getId))
                .switchIfEmpty(Mono.error(new GenericException(GenericErrorEnum.PAYLOAD_DOES_NOT_CONTAIN_MINIMUM_VALIDATION)));

        return Mono.just(sale)
                .filter(this::validateData)
                .flatMap(saleDTO -> validateIfClientExists(sale))
                .flatMap(saleDTO -> buildTotalPriceAndNameByProduct(sale, productListMap))
                .flatMap(saleDTO -> Mono.just(saleDTO)
                        .flatMap(this::buildSaleAndSave)
                        .flatMap(saleSaved -> buildSaleDetailAndSave(saleDTO, saleSaved))
                        .flatMap(saleSaved -> updateProductStock(saleDTO, productListMap)))
                .switchIfEmpty(Mono.error(new GenericException(GenericErrorEnum.PAYLOAD_DOES_NOT_CONTAIN_MINIMUM_VALIDATION)))
                .doOnError(error -> log.severe("*** ERROR IN SaleCommandUseCase :: saveSale " + error.getMessage()));
    }

    private boolean validateData(Sale sale) {
        return Objects.nonNull(sale.getClientId())
                && !sale.getClientId().isEmpty()
                && Objects.nonNull(sale.getSaleDetails())
                && !sale.getSaleDetails().isEmpty();
    }

    private Mono<Sale> updateProductStock(Sale saleDTO, Mono<Map<String, Product>> productListMap) {
        return Flux.fromIterable(saleDTO.getSaleDetails())
                .flatMap(saleDetail -> productListMap.map(productData -> productData.get(saleDetail.getProductId()))
                        .map(product -> product.toBuilder()
                                .stock(product.getStock() - saleDetail.getProductAmount())
                                .build())
                        .flatMap(productRepository::saveProduct))
                .then(Mono.just(saleDTO));
    }

    private Mono<Sale> validateIfClientExists(Sale sale) {
        return clientRepository.findByClientId(sale.getClientId())
                .switchIfEmpty(Mono.error(new ClientException(ClientErrorEnum.CLIENT_NOT_FOUND)))
                .thenReturn(sale);
    }

    private Mono<Sale> buildSaleDetailAndSave(Sale saleDTO, Sale saleSaved) {
        return Flux.fromIterable(saleDTO.getSaleDetails())
                .map(saleDetail -> saleDetail.toBuilder()
                        .id(randomId())
                        .saleId(saleSaved.getId())
                        .build())
                .collectList()
                .flatMap(saleDetailRepository::saveAll)
                .thenReturn(saleDTO);
    }

    private Mono<Sale> buildSaleAndSave(Sale saleData) {
        return Mono.just(saleData)
                .map(sale -> Sale.builder()
                        .id(randomId())
                        .clientId(saleData.getClientId())
                        .creationDate(getActualTime())
                        .totalSale(calculateTotalSale(saleData))
                        .build())
                .flatMap(saleRepository::saveSale);
    }

    private long calculateTotalSale(Sale saleData) {
        return saleData.getSaleDetails().stream()
                .mapToLong(SaleDetail::getTotalProductPrice)
                .sum();
    }

    private Mono<Sale> buildTotalPriceAndNameByProduct(Sale sale, Mono<Map<String, Product>> productListMap) {
        return Flux.fromIterable(sale.getSaleDetails())
                .flatMap(saleDetail -> Mono.just(saleDetail)
                        .flatMap(saleDetailDTO -> validateProductExistsAndStock(saleDetailDTO, productListMap))
                        .map(product -> saleDetail.toBuilder()
                                .productName(product.getName())
                                .totalProductPrice(saleDetail.getProductAmount() * saleDetail.getProductPrice())
                                .build()))
                .collectList()
                .map(saleDetails -> sale.toBuilder()
                        .saleDetails(saleDetails)
                        .build());
    }

    private Mono<Product> validateProductExistsAndStock(SaleDetail saleDetailDTO, Mono<Map<String, Product>> productListMap) {
        return productListMap.map(productData -> productData.get(saleDetailDTO.getProductId()))
                .switchIfEmpty(Mono.error(new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND)))
                .filter(product -> product.getStock() > ZERO && product.getStock() >= saleDetailDTO.getProductAmount())
                .switchIfEmpty(Mono.error(new ProductException(ProductErrorEnum.PRODUCT_IS_OUT_OF_STOCK)));
    }

}
