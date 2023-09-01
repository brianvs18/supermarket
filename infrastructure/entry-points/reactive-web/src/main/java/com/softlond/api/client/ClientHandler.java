package com.softlond.api.client;

import com.softlond.model.client.Client;
import com.softlond.usecase.clientusecase.ClientHandlerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/clients")
@RequiredArgsConstructor
public class ClientHandler {

    private final ClientHandlerUseCase clientHandlerUseCase;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<Client> findAll() {
        return clientHandlerUseCase.findAllClients();
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Client> findById(@RequestParam(value = "id") final String id) {
        return clientHandlerUseCase.findByClientId(id);
    }
}
