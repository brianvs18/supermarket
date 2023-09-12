package com.softlond.jpa.supermarketdb.sale;

import com.softlond.jpa.helper.AdapterOperations;
import com.softlond.model.sale.Sale;
import com.softlond.model.sale.gateways.SaleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SaleRepositoryAdapter extends AdapterOperations<Sale, SaleEntity, String, JpaSaleRepository> implements SaleRepository {

    protected SaleRepositoryAdapter(JpaSaleRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, Sale.class));
    }

    @Override
    public Flux<Sale> findAllSales() {
        return Flux.fromIterable(repository.findAllSales())
                .map(this::toEntity);
    }

    @Override
    public Mono<Sale> findBySaleId(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    @Override
    public Flux<Sale> findAllByFilters(String clientId, Long initialDate, Long finalDate) {
        return Flux.fromIterable(repository.findAllByFilters(clientId, initialDate, finalDate))
                .map(this::toEntity);
    }

    @Override
    public Mono<Sale> saveSale(Sale sale) {
        return Mono.justOrEmpty(sale)
                .map(this::toData)
                .map(repository::save)
                .map(this::toEntity);
    }
}
