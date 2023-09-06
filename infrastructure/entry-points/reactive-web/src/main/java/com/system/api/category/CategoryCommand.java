package com.system.api.category;

import com.system.api.dto.CategoryDTO;
import com.system.model.category.Category;
import com.system.modelconverter.ModelMapper;
import com.system.usecase.categoryusecase.CategoryCommandUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryCommand extends ModelMapper<Category, CategoryDTO> {

    private final CategoryCommandUseCase categoryCommandUseCase;

    protected CategoryCommand(ObjectMapper mapper, CategoryCommandUseCase categoryCommandUseCase) {
        super(mapper);
        this.categoryCommandUseCase = categoryCommandUseCase;
    }

    @PostMapping("/category")
    public Mono<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryCommandUseCase.saveCategory(this.dtoToModel(categoryDTO))
                .map(this::modelToDTO);
    }

    @DeleteMapping("/category")
    public Mono<Void> deleteCategory(@RequestParam(value = "id") final String id) {
        return categoryCommandUseCase.deleteByCategoryId(id);
    }
}
