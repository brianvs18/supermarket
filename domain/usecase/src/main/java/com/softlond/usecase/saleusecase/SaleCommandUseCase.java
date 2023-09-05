package com.softlond.usecase.saleusecase;

import com.softlond.model.client.gateways.ClientRepository;
import com.softlond.model.enums.ClientErrorEnum;
import com.softlond.model.enums.ProductErrorEnum;
import com.softlond.model.exceptions.ClientException;
import com.softlond.model.exceptions.ProductException;
import com.softlond.model.product.Product;
import com.softlond.model.product.gateways.ProductRepository;
import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.SaleDetail;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.softlond.model.utils.DateFunctions.getActualTime;
import static com.softlond.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class SaleCommandUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public Mono<Sale> saveSale(Sale sale) {
        return Mono.just(sale)
                .flatMap(saleDTO -> validateIfClientExists(sale))
                .flatMap(saleDTO -> calculateTotalPriceByProduct(sale))
                .flatMap(saleDTO -> Mono.just(saleDTO)
                        .flatMap(this::buildSaleAndSave)
                        .flatMap(saleSaved -> buildSaleDetailAndSave(saleDTO, saleSaved))
                        .flatMap(saleSaved -> updateProductStock(saleDTO))
                );
    }

    private Mono<Sale> updateProductStock(Sale saleDTO) {
        return Flux.fromIterable(saleDTO.getSaleDetails())
                .flatMap(saleDetail -> getByProductId(saleDetail)
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

    private Mono<Sale> calculateTotalPriceByProduct(Sale sale) {
        return Flux.fromIterable(sale.getSaleDetails())
                .flatMap(saleDetail -> Mono.just(saleDetail)
                        .flatMap(this::validateProductExistsAndStock)
                        .map(product -> saleDetail.toBuilder()
                                .totalProductPrice(saleDetail.getProductAmount() * saleDetail.getProductPrice())
                                .build()))
                .collectList()
                .map(saleDetails -> sale.toBuilder()
                        .saleDetails(saleDetails)
                        .build());
    }

    private Mono<Product> validateProductExistsAndStock(SaleDetail saleDetailDTO) {
        return getByProductId(saleDetailDTO)
                .switchIfEmpty(Mono.error(new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND)))
                .filter(product -> product.getStock() > 0 && product.getStock() >= saleDetailDTO.getProductAmount())
                .switchIfEmpty(Mono.error(new ProductException(ProductErrorEnum.NO_AVAILABLE_STOCK)));
    }

    private Mono<Product> getByProductId(SaleDetail saleDetailDTO) {
        return productRepository.findByProductId(saleDetailDTO.getProductId());
    }
}
