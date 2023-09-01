package com.softlond.api.category;

import com.softlond.model.category.Category;
import com.softlond.usecase.categoryusecase.CategoryCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryCommand {

    private final CategoryCommandUseCase categoryCommandUseCase;

    @PostMapping("/category")
    public Mono<Category> saveCategory(@RequestBody Category category) {
        return categoryCommandUseCase.saveCategory(category);
    }

    @DeleteMapping("/category")
    public Mono<Void> deleteCategory(@RequestParam(value = "id") final String id) {
        return categoryCommandUseCase.deleteByCategoryId(id);
    }
}
