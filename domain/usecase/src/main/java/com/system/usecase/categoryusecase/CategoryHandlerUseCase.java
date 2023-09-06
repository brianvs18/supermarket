package com.system.usecase.categoryusecase;

import com.system.model.category.Category;
import com.system.model.category.gateways.CategoryRepository;
import com.system.model.enums.CategoryErrorEnum;
import com.system.model.exceptions.CategoryException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CategoryHandlerUseCase {

    private final CategoryRepository categoryRepository;

    public Flux<Category> findAllCategories() {
        return categoryRepository.findAllCategories();
    }

    public Mono<Category> findByCategoryId(String id) {
        return categoryRepository.findByCategoryId(id)
                .switchIfEmpty(Mono.error(new CategoryException(CategoryErrorEnum.CATEGORY_NOT_FOUND)));
    }

}
