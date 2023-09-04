package com.softlond.model.saledetail.gateways;

import com.softlond.model.saledetail.SaleDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SaleDetailRepository {

    Mono<Void> saveAll(List<SaleDetail> saleDetails);

    Flux<SaleDetail> findAllBySaleId(String saleId);
}
