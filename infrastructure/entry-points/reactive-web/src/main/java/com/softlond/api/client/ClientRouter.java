package com.softlond.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouter {

    @Bean(name = "clientRouterFunction")
    public RouterFunction<ServerResponse> routerFunction(ClientHandler handler, ClientCommand command) {
        return route(GET("/api/client"), request -> handler.findAllClients())
                .andRoute(GET("/api/client/find-by-id"), handler::findByClientId)
                .andRoute(POST("/api/client"), command::saveClient);
    }
}
