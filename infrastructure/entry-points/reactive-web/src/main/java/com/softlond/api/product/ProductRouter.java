package com.softlond.api.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean(name = "productRouterFunction")
    public RouterFunction<ServerResponse> routerFunction(ProductHandler handler, ProductCommand command) {
        return route(GET("/api/product"), request -> handler.findAllProducts())
                .andRoute(GET("/api/product/find-by-id"), handler::findByProductId)
                .andRoute(POST("/api/product"), command::saveProduct);
    }
}
