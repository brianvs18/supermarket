package com.softlond.usecase.saleusecase;

import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class SaleHandlerUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public Flux<Sale> findAllSales() {
        return null;
    }
}
