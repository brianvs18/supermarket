package com.softlond.api.sale;

import com.softlond.model.sale.Sale;
import com.softlond.usecase.saleusecase.SaleHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/sales")
@RequiredArgsConstructor
public class SaleHandler {

    private final SaleHandlerUseCase saleHandlerUseCase;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<Sale> findAll() {
        return saleHandlerUseCase.findAllSales();
    }
}
