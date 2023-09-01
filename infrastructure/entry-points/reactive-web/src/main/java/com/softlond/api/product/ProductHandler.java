package com.softlond.api.product;

import com.softlond.usecase.productusecase.ProductHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductHandlerUseCase productHandlerUseCase;

    public Mono<ServerResponse> findAllProducts(){
        return productHandlerUseCase.findAllProducts()
                .collectList()
                .flatMap(products -> ServerResponse.ok().bodyValue(products))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByProductId(ServerRequest serverRequest){
        String productId = serverRequest.queryParam("id").orElse("");
        return productHandlerUseCase.findByProductId(productId)
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
