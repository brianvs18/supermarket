package com.system.model.product.gateways;

import com.system.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Flux<Product> findAllProducts();
    Mono<Product> findByProductId(String id);
    Mono<Product> saveProduct(Product product);
}
