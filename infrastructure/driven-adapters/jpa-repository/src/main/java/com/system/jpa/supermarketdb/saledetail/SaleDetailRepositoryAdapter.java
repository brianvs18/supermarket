package com.system.jpa.supermarketdb.saledetail;

import com.system.jpa.helper.AdapterOperations;
import com.system.model.saledetail.SaleDetail;
import com.system.model.saledetail.gateways.SaleDetailRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SaleDetailRepositoryAdapter extends AdapterOperations<SaleDetail, SaleDetailEntity, String, JpaSaleDetailRepository> implements SaleDetailRepository {

    protected SaleDetailRepositoryAdapter(JpaSaleDetailRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, SaleDetail.class));
    }

    @Override
    public Mono<Void> saveAll(List<SaleDetail> saleDetails) {
        return Flux.fromIterable(saleDetails)
                .map(this::toData)
                .collectList()
                .flatMapIterable(repository::saveAll)
                .then();
    }

    @Override
    public Flux<SaleDetail> findAllBySaleId(String saleId) {
        return Flux.fromIterable(repository.findAllBySaleId(saleId))
                .map(this::toEntity);
    }
}
