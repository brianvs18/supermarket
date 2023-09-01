package com.softlond.usecase.clientusecase;

import com.softlond.model.client.Client;
import com.softlond.model.client.gateways.ClientRepository;
import com.softlond.model.enums.ClientErrorEnum;
import com.softlond.model.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ClientHandlerUseCase {

    private final ClientRepository clientRepository;

    public Flux<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

    public Mono<Client> findByClientId(String id) {
        return clientRepository.findByClientId(id)
                .switchIfEmpty(Mono.error(new ClientException(ClientErrorEnum.CLIENT_NOT_FOUND)));
    }

    public Mono<Client> findByDocument(String document) {
        return clientRepository.findByDocument(document)
                .switchIfEmpty(Mono.error(new ClientException(ClientErrorEnum.CLIENT_NOT_FOUND)));
    }
}
