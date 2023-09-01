package com.softlond.jpa.supermarketdb.client;

import com.softlond.jpa.helper.AdapterOperations;
import com.softlond.model.client.Client;
import com.softlond.model.client.gateways.ClientRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientRepositoryAdapter extends AdapterOperations<Client, ClientEntity, String, JpaClientRepository> implements ClientRepository {
    protected ClientRepositoryAdapter(JpaClientRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, Client.class));
    }

    @Override
    public Flux<Client> findAllClients() {
        return Flux.fromIterable(repository.findAllClients())
                .map(this::toEntity);
    }

    @Override
    public Mono<Client> findByClientId(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    @Override
    public Mono<Client> findByDocument(String document) {
        return Mono.justOrEmpty(repository.findByDocument(document))
                .map(this::toEntity);
    }

    @Override
    public Mono<Client> saveClient(Client client) {
        return Mono.justOrEmpty(client)
                .map(this::toData)
                .map(repository::save)
                .map(this::toEntity);
    }
}
