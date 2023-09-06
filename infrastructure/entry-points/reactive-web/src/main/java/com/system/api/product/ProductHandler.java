package com.system.api.product;

import com.system.api.dto.ProductDTO;
import com.system.model.product.Product;
import com.system.modelconverter.ModelMapper;
import com.system.usecase.productusecase.ProductHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/products")
public class ProductHandler extends ModelMapper<Product, ProductDTO> {

    private final ProductHandlerUseCase productHandlerUseCase;

    protected ProductHandler(ObjectMapper mapper, ProductHandlerUseCase productHandlerUseCase) {
        super(mapper);
        this.productHandlerUseCase = productHandlerUseCase;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<ProductDTO> findAll() {
        return productHandlerUseCase.findAllProducts().map(this::modelToDTO);
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ProductDTO> findById(@RequestParam(value = "id") final String id) {
        return productHandlerUseCase.findByProductId(id).map(this::modelToDTO);
    }
}
