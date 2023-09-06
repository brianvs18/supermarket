package com.system.model.category.gateways;

import com.system.model.category.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository {

    Flux<Category> findAllCategories();
    Mono<Category> findByCategoryId(String id);
    Mono<Category> saveCategory(Category category);
    Mono<Void> deleteByCategoryId(String id);
}
