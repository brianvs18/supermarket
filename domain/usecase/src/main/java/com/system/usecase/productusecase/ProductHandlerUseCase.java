package com.system.usecase.productusecase;

import com.system.model.enums.ProductErrorEnum;
import com.system.model.exceptions.ProductException;
import com.system.model.product.Product;
import com.system.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductHandlerUseCase {

    private final ProductRepository productRepository;

    public Flux<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public Mono<Product> findByProductId(String id) {
        return productRepository.findByProductId(id)
                .switchIfEmpty(Mono.error(new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND)));
    }
}
