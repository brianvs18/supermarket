package com.softlond.usecase.saleusecase;

import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class SaleHandlerUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    Logger log = Logger.getLogger(SaleHandlerUseCase.class.getName());

    public Flux<Sale> findAllSales() {
        log.info("*** ENTER TO SaleHandlerUseCase :: findAllSales");
        return saleRepository.findAllSales()
                .flatMap(sale -> saleDetailRepository.findAllBySaleId(sale.getId())
                        .collectList()
                        .map(saleDetailList -> sale.toBuilder()
                                .saleDetails(saleDetailList)
                                .build())
                );
    }

    public Flux<Sale> findAllByDate(Long date) {
        log.info("*** ENTER TO SaleHandlerUseCase :: findAllByDate");
        return saleRepository.findAllByDate(date);
    }

    public Flux<Sale> findAllByClientId(String clientId) {
        log.info("*** ENTER TO SaleHandlerUseCase :: findAllByClientId");
        return saleRepository.findAllByClientId(clientId);
    }
}
