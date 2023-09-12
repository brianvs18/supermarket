package com.softlond.usecase.saleusecase;

import com.softlond.model.enums.SaleErrorEnum;
import com.softlond.model.exceptions.SaleException;
import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class SaleHandlerUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    Logger log = Logger.getLogger(SaleHandlerUseCase.class.getName());

    public Flux<Sale> findAllSales() {
        log.info("*** ENTER TO SaleHandlerUseCase :: findAllSales");
        return saleRepository.findAllSales()
                .flatMap(this::buildSaleDetails)
                .doOnError(error -> log.severe("*** ERROR IN SaleHandlerUseCase :: findAllSales " + error.getMessage()));
    }

    private Mono<Sale> buildSaleDetails(Sale sale) {
        return saleDetailRepository.findAllBySaleId(sale.getId())
                .collectList()
                .map(saleDetailList -> sale.toBuilder()
                        .saleDetails(saleDetailList)
                        .build());
    }

    public Flux<Sale> findAllByFilters(String clientId, Long initialDate, Long finalDate) {
        log.info("*** ENTER TO SaleHandlerUseCase :: findAllByFilters");
        return Flux.just(clientId)
                .filter(client -> !validateFields(clientId, initialDate, finalDate))
                .flatMap(string -> saleRepository.findAllByFilters(clientId, initialDate, finalDate)
                        .flatMap(this::buildSaleDetails))
                .switchIfEmpty(Mono.error(new SaleException(SaleErrorEnum.ERROR_IN_FILTERS)))
                .doOnError(error -> log.severe("*** ERROR IN SaleHandlerUseCase :: findAllByFilters " + error.getMessage()));
    }

    private boolean validateFields(String clientId, Long initialDate, Long finalDate) {
        return Objects.nonNull(finalDate) && (clientId.isEmpty() && Objects.isNull(initialDate));
    }
}
