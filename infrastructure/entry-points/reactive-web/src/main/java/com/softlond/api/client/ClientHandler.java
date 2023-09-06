package com.softlond.api.client;

import com.softlond.api.dto.ClientDTO;
import com.softlond.model.client.Client;
import com.softlond.modeldriver.ModelMapper;
import com.softlond.usecase.clientusecase.ClientHandlerUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientHandler extends ModelMapper<Client, ClientDTO> {

    private final ClientHandlerUseCase clientHandlerUseCase;

    protected ClientHandler(ObjectMapper mapper, ClientHandlerUseCase clientHandlerUseCase) {
        super(mapper);
        this.clientHandlerUseCase = clientHandlerUseCase;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<ClientDTO> findAll() {
        return clientHandlerUseCase.findAllClients()
                .map(this::modelToDTO);
    }

    @GetMapping(path = "/find-by-id", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ClientDTO> findById(@RequestParam(value = "id") final String id) {
        return clientHandlerUseCase.findByClientId(id)
                .map(this::modelToDTO);
    }
}
