package com.softlond.api.product;

import com.softlond.model.product.Product;
import com.softlond.usecase.productusecase.ProductHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductHandlerUseCase productHandlerUseCase;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<Product> findAll() {
        return productHandlerUseCase.findAllProducts();
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Product> findById(@RequestParam(value = "id") final String id) {
        return productHandlerUseCase.findByProductId(id);
    }
}
