package com.softlond.api.client;

import com.softlond.usecase.clientusecase.ClientHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientHandler {

    private final ClientHandlerUseCase clientHandlerUseCase;

    public Mono<ServerResponse> findAllClients() {
        return clientHandlerUseCase.findAllClients()
                .collectList()
                .flatMap(clients -> ServerResponse.ok().bodyValue(clients))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByClientId(ServerRequest serverRequest) {
        String clientId = serverRequest.queryParam("id").orElse("");
        return clientHandlerUseCase.findByClientId(clientId)
                .flatMap(client -> ServerResponse.ok().bodyValue(client))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
