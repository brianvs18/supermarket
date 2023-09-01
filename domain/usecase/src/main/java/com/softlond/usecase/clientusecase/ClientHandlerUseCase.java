package com.softlond.usecase.clientusecase;

import com.softlond.model.client.Client;
import com.softlond.model.client.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ClientHandlerUseCase {

    private final ClientRepository clientRepository;

    public Flux<Client> findAllClients(){
        return clientRepository.findAllClients();
    }

    public Mono<Client> findByClientId(String id){
        return clientRepository.findByClientId(id);
    }

    public Mono<Client> findByDocument(String document){
        return clientRepository.findByDocument(document);
    }
}
