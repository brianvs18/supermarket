package com.softlond.api.product;

import com.softlond.model.product.Product;
import com.softlond.usecase.productusecase.ProductCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
public class ProductCommand {

    private final ProductCommandUseCase productCommandUseCase;

    @PostMapping("/product")
    public Mono<Product> saveClient(@RequestBody Product product) {
        return productCommandUseCase.saveProduct(product);
    }
}
