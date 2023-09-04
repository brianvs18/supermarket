package com.softlond.usecase.saleusecase;

import com.softlond.model.client.gateways.ClientRepository;
import com.softlond.model.enums.ClientErrorEnum;
import com.softlond.model.exceptions.ClientException;
import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.SaleDetail;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Clock;

import static com.softlond.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class SaleCommandUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ClientRepository clientRepository;

    public Mono<Sale> saveSale(Sale sale) {
        return Mono.just(sale)
                .flatMap(saleDTO -> validateIfClientExists(sale))
                .flatMap(saleDTO -> calculateTotalPriceByProduct(sale))
                .flatMap(saleDTO -> Mono.just(saleDTO)
                        .flatMap(this::buildSaleAndSave)
                        .flatMap(saleSaved -> buildSaleDetailAndSave(saleDTO, saleSaved)));
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
                        .creationDate(Clock.systemDefaultZone().millis())
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
                .map(saleDetail -> saleDetail.toBuilder()
                        .totalProductPrice(saleDetail.getProductAmount() * saleDetail.getProductPrice())
                        .build())
                .collectList()
                .map(saleDetails -> sale.toBuilder()
                        .saleDetails(saleDetails)
                        .build());
    }
}
