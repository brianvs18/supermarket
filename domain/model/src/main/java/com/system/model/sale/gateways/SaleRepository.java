package com.system.model.sale.gateways;

import com.system.model.sale.Sale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleRepository {

    Flux<Sale> findAllSales();

    Mono<Sale> findBySaleId(String id);

    Flux<Sale> findAllByClientId(String clientId);

    Flux<Sale> findAllByDate(Long date);

    Flux<Sale> findAllByClientIdAndBetweenDate(String clientId, Long initialDate, Long finalDate);

    Mono<Sale> saveSale(Sale sale);
}
