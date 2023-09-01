package com.softlond.model.product.gateways;

import com.softlond.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Flux<Product> findAllProducts();
    Mono<Product> findByProductId(String id);
    Mono<Product> saveProduct(Product product);
}
