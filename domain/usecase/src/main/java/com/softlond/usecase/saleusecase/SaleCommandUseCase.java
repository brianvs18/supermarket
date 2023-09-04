package com.softlond.usecase.saleusecase;

import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import com.softlond.model.saledetail.gateways.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SaleCommandUseCase {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public Mono<Sale> saveSale(Sale sale) {
        return null;
    }
}
