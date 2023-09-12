package com.softlond.model.sale.gateways;

import com.softlond.model.sale.Sale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleRepository {

    Flux<Sale> findAllSales();

    Mono<Sale> findBySaleId(String id);

    Flux<Sale> findAllByFilters(String clientId, Long initialDate, Long finalDate);

    Mono<Sale> saveSale(Sale sale);
}
