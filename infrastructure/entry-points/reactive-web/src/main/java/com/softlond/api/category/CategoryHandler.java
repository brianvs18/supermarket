package com.softlond.api.category;


import com.softlond.api.dto.CategoryDTO;
import com.softlond.model.category.Category;
import com.softlond.modelconverter.ModelMapper;
import com.softlond.usecase.categoryusecase.CategoryHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryHandler extends ModelMapper<Category, CategoryDTO> {

    private final CategoryHandlerUseCase categoryHandlerUseCase;

    protected CategoryHandler(ObjectMapper mapper, CategoryHandlerUseCase categoryHandlerUseCase) {
        super(mapper);
        this.categoryHandlerUseCase = categoryHandlerUseCase;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<CategoryDTO> findAll() {
        return categoryHandlerUseCase.findAllCategories()
                .map(this::modelToDTO);
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<CategoryDTO> findById(@RequestParam(value = "id") final String id) {
        return categoryHandlerUseCase.findByCategoryId(id)
                .map(this::modelToDTO);
    }

}
