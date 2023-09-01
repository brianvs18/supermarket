package com.softlond.api.client;

import com.softlond.model.client.Client;
import com.softlond.usecase.clientusecase.ClientCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/clients")
@RequiredArgsConstructor
public class ClientCommand {

    private final ClientCommandUseCase clientCommandUseCase;

    @PostMapping("/client")
    public Mono<Client> saveClient(@RequestBody Client client) {
        return clientCommandUseCase.saveClient(client);
    }

}
