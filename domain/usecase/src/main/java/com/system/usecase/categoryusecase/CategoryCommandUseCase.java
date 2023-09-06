package com.system.usecase.categoryusecase;

import com.system.model.category.Category;
import com.system.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.system.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class CategoryCommandUseCase {

    private final CategoryRepository categoryRepository;

    public Mono<Category> saveCategory(Category category){
        return Mono.just(category)
                .filter(categoryDTO -> Objects.nonNull(category.getId()) && !category.getId().isEmpty())
                .flatMap(this::buildUpdateCategory)
                .switchIfEmpty(buildNewCategory(category))
                .flatMap(categoryRepository::saveCategory);
    }

    private Mono<Category> buildNewCategory(Category category) {
        return Mono.just(category)
                .map(categoryDTO -> Category.builder()
                        .id(randomId())
                        .name(category.getName())
                        .build());
    }

    private Mono<Category> buildUpdateCategory(Category category) {
        return categoryRepository.findByCategoryId(category.getId())
                .map(categoryModel -> category.toBuilder()
                        .id(categoryModel.getId())
                        .name(category.getName())
                        .build());
    }

    public Mono<Void> deleteByCategoryId(String id){
        return categoryRepository.deleteByCategoryId(id);
    }
}
