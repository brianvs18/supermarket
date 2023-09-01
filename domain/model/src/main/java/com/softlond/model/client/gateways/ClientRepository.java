package com.softlond.model.client.gateways;

import com.softlond.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {

    Flux<Client> findAllClients();
    Mono<Client> findByClientId(String id);
    Mono<Client> findByDocument(String document);
    Mono<Client> saveClient(Client client);
}
