package com.softlond.api.category;

import com.softlond.api.dto.CategoryDTO;
import com.softlond.model.category.Category;
import com.softlond.modelconverter.ModelMapper;
import com.softlond.usecase.categoryusecase.CategoryCommandUseCase;
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
