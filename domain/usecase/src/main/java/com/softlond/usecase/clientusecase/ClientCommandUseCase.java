package com.softlond.usecase.clientusecase;

import com.softlond.model.client.Client;
import com.softlond.model.client.gateways.ClientRepository;
import com.softlond.model.enums.ClientErrorEnum;
import com.softlond.model.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.logging.Logger;

import static com.softlond.model.utils.GenerateId.randomId;

@RequiredArgsConstructor
public class ClientCommandUseCase {

    private final ClientRepository clientRepository;
    Logger log = Logger.getLogger(ClientCommandUseCase.class.getName());

    public Mono<Client> saveClient(Client client) {
        log.info("*** ENTER TO ClientCommandUseCase :: saveClient - document: " + client.getDocument());
        return Mono.just(client)
                .filter(clientData -> Objects.nonNull(client.getId()) && !client.getId().isEmpty())
                .flatMap(clientData -> buildUpdateClient(client))
                .switchIfEmpty(buildNewClient(client))
                .flatMap(clientRepository::saveClient)
                .doOnError(error -> log.severe("*** ERROR IN ClientCommandUseCase :: saveClient " + error.getMessage()))
                .doOnSuccess(success -> log.info("Client saved successfully"));
    }

    private Mono<Client> buildUpdateClient(Client client) {
        return clientRepository.findByClientId(client.getId())
                .switchIfEmpty(Mono.error(new ClientException(ClientErrorEnum.CLIENT_NOT_FOUND)))
                .filter(clientModel -> clientModel.getDocument().equals(client.getDocument()))
                .map(clientModel -> client.toBuilder()
                        .id(clientModel.getId())
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .document(client.getDocument())
                        .build())
                .switchIfEmpty(validateIfDocumentAlreadyRegistered(client)
                        .map(clientDTO -> client.toBuilder()
                                .id(clientDTO.getId())
                                .name(client.getName())
                                .lastname(client.getLastname())
                                .document(client.getDocument())
                                .build()));
    }

    private Mono<Client> buildNewClient(Client client) {
        return Mono.just(client)
                .flatMap(this::validateIfDocumentAlreadyRegistered)
                .map(clientDTO -> Client.builder()
                        .id(randomId())
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .document(client.getDocument())
                        .build());
    }

    private Mono<Client> validateIfDocumentAlreadyRegistered(Client client) {
        return Mono.just(client)
                .flatMap(clientDTO -> clientRepository.findByDocument(client.getDocument())
                        .flatMap(clientModel -> Mono.just(clientModel)
                                .filter(clientData -> clientModel.getId().equals(client.getId()))
                                .switchIfEmpty(Mono.error(new ClientException(ClientErrorEnum.DOCUMENT_ALREADY_REGISTERED)))))
                .defaultIfEmpty(client);
    }

}
