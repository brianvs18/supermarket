package com.softlond.api.category;

import com.softlond.model.category.Category;
import com.softlond.usecase.categoryusecase.CategoryCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryCommand {

    private final CategoryCommandUseCase categoryCommandUseCase;

    public Mono<ServerResponse> saveCategory(ServerRequest serverRequest) {
        Mono<Category> categorySave = serverRequest.bodyToMono(Category.class);
        return categorySave
                .flatMap(categoryCommandUseCase::saveCategory)
                .flatMap(categorySaved -> ServerResponse.ok().bodyValue(categorySaved))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> deleteCategory(ServerRequest serverRequest) {
        String categoryId = serverRequest.queryParam("id").orElse("");
        return categoryCommandUseCase.deleteByCategoryId(categoryId)
                .then(ServerResponse.noContent().build())
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }
}
