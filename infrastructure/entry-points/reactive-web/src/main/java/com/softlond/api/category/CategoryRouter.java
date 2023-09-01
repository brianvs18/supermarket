package com.softlond.api.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CategoryRouter {

    @Bean(name = "categoryRouterFunction")
    public RouterFunction<ServerResponse> routerFunction(CategoryHandler handler, CategoryCommand command) {
        return route(GET("/api/category"), request -> handler.findAllCategories())
                .andRoute(GET("/api/category/find-by-id"), handler::findByCategoryId)
                .andRoute(POST("/api/category"), command::saveCategory)
                .andRoute(DELETE("/api/category/id"), command::deleteCategory);
    }
}
