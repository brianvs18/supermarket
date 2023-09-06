package com.system.jpa.supermarketdb.product;

import com.system.jpa.helper.AdapterOperations;
import com.system.model.product.Product;
import com.system.model.product.gateways.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductEntity, String, JpaProductRepository> implements ProductRepository {
    protected ProductRepositoryAdapter(JpaProductRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, Product.class));
    }

    @Override
    public Flux<Product> findAllProducts() {
        return Flux.fromIterable(repository.findAllProducts())
                .map(this::toEntity);
    }

    @Override
    public Mono<Product> findByProductId(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return Mono.justOrEmpty(product)
                .map(this::toData)
                .map(repository::save)
                .map(this::toEntity);
    }
}
