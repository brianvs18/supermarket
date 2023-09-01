package com.softlond.usecase.productusecase;

import com.softlond.model.product.Product;
import com.softlond.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.softlond.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class ProductCommandUseCase {

    private final ProductRepository productRepository;

    public Mono<Product> saveProduct(Product product) {
        return Mono.just(product)
                .filter(productDTO -> Objects.nonNull(product.getId()) && !product.getId().isEmpty())
                .flatMap(productDTO -> buildUpdateProduct(product))
                .switchIfEmpty(buildNewProduct(product))
                .flatMap(productRepository::saveProduct);
    }

    private static Mono<Product> buildNewProduct(Product product) {
        return Mono.just(product)
                .map(product1 -> Product.builder()
                        .id(randomId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .categoryId(product.getCategoryId())
                        .build());
    }

    private Mono<Product> buildUpdateProduct(Product product) {
        return productRepository.findByProductId(product.getId())
                .map(productModel -> product.toBuilder()
                        .id(productModel.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .categoryId(product.getCategoryId())
                        .build());
    }
}
