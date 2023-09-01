package com.softlond.api.client;

import com.softlond.model.client.Client;
import com.softlond.usecase.clientusecase.ClientCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientCommand {

    private final ClientCommandUseCase clientCommandUseCase;

    public Mono<ServerResponse> saveClient(ServerRequest serverRequest) {
        Mono<Client> clientSave = serverRequest.bodyToMono(Client.class);
        return clientSave
                .flatMap(clientCommandUseCase::saveClient)
                .flatMap(client -> ServerResponse.ok().bodyValue(client))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
