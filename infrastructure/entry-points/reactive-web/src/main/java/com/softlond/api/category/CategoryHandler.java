package com.softlond.api.category;

import com.softlond.model.category.Category;
import com.softlond.usecase.categoryusecase.CategoryHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryHandlerUseCase categoryHandlerUseCase;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<Category> findAll() {
        return categoryHandlerUseCase.findAllCategories();
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Category> findById(@RequestParam(value = "id") final String id) {
        return categoryHandlerUseCase.findByCategoryId(id);
    }

}
