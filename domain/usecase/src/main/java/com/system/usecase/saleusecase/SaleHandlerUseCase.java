package com.system.usecase.saleusecase;

import com.system.model.sale.Sale;
import com.system.model.sale.gateways.SaleRepository;
import com.system.model.saledetail.gateways.SaleDetailRepository;
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
        return null;
    }
}
