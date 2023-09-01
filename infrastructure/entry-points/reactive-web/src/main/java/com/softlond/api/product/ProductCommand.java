package com.softlond.api.product;

import com.softlond.model.product.Product;
import com.softlond.usecase.productusecase.ProductCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductCommand {

    private final ProductCommandUseCase productCommandUseCase;

    public Mono<ServerResponse> saveProduct(ServerRequest serverRequest) {
        Mono<Product> productSave = serverRequest.bodyToMono(Product.class);
        return productSave
                .flatMap(productCommandUseCase::saveProduct)
                .flatMap(productSaved -> ServerResponse.ok().bodyValue(productSaved))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
