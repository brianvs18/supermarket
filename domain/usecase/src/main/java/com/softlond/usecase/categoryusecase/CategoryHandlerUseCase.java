package com.softlond.usecase.categoryusecase;

import com.softlond.model.category.Category;
import com.softlond.model.category.gateways.CategoryRepository;
import com.softlond.model.enums.CategoryErrorEnum;
import com.softlond.model.exceptions.CategoryException;
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
