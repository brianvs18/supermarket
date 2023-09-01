package com.softlond.usecase.productusecase;

import com.softlond.model.product.Product;
import com.softlond.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductHandlerUseCase {

    private final ProductRepository productRepository;

    public Flux<Product> findAllProducts(){
        return productRepository.findAllProducts();
    }

    public Mono<Product> findByProductId(String id){
        return productRepository.findByProductId(id);
    }
}
