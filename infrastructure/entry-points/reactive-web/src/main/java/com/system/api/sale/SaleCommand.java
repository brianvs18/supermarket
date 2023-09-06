package com.system.api.sale;

import com.system.model.sale.Sale;
import com.system.usecase.saleusecase.SaleCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/sales")
@RequiredArgsConstructor
public class SaleCommand {

    private final SaleCommandUseCase saleCommandUseCase;

    @PostMapping("/sale")
    public Mono<Sale> saveSale(@RequestBody Sale sale) {
        return saleCommandUseCase.saveSale(sale);
    }
}
