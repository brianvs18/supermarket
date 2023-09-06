package com.system.api.product;

import com.system.api.dto.ProductDTO;
import com.system.model.product.Product;
import com.system.modelconverter.ModelMapper;
import com.system.usecase.productusecase.ProductCommandUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/products")
public class ProductCommand extends ModelMapper<Product, ProductDTO> {

    private final ProductCommandUseCase productCommandUseCase;

    protected ProductCommand(ObjectMapper mapper, ProductCommandUseCase productCommandUseCase) {
        super(mapper);
        this.productCommandUseCase = productCommandUseCase;
    }

    @PostMapping("/product")
    public Mono<ProductDTO> saveClient(@RequestBody ProductDTO productDTO) {
        return productCommandUseCase.saveProduct(this.dtoToModel(productDTO))
                .map(this::modelToDTO);
    }
}
