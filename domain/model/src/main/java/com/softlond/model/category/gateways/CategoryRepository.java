package com.softlond.model.category.gateways;

import com.softlond.model.category.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository {

    Flux<Category> findAllCategories();
    Mono<Category> findByCategoryId(String id);
    Mono<Category> saveCategory(Category category);
    Mono<Void> deleteByCategoryId(String id);
}
