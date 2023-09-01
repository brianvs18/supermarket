package com.softlond.usecase.categoryusecase;

import com.softlond.model.category.Category;
import com.softlond.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CategoryHandlerUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Category> findByCategoryId(String id) {
        return categoryRepository.findByCategoryId(id);
    }

    public Flux<Category> findAllCategories(){
        return categoryRepository.findAllCategories();
    }
}
