package com.system.model.client.gateways;

import com.system.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {

    Flux<Client> findAllClients();
    Mono<Client> findByClientId(String id);
    Mono<Client> findByDocument(String document);
    Mono<Client> saveClient(Client client);
}
