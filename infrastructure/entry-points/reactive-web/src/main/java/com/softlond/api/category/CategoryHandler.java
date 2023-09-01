package com.softlond.api.category;

import com.softlond.usecase.categoryusecase.CategoryHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryHandlerUseCase categoryHandlerUseCase;

    public Mono<ServerResponse> findAllCategories() {
        return categoryHandlerUseCase.findAllCategories()
                .collectList()
                .flatMap(categories -> ServerResponse.ok().bodyValue(categories))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByCategoryId(ServerRequest serverRequest) {
        String categoryId = serverRequest.queryParam("id").orElse("");
        return categoryHandlerUseCase.findByCategoryId(categoryId)
                .flatMap(category -> ServerResponse.ok().bodyValue(category))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
