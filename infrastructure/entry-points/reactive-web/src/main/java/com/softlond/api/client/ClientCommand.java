package com.softlond.api.client;

import com.softlond.api.dto.ClientDTO;
import com.softlond.model.client.Client;
import com.softlond.modeldriver.ModelMapper;
import com.softlond.usecase.clientusecase.ClientCommandUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientCommand extends ModelMapper<Client, ClientDTO> {

    private final ClientCommandUseCase clientCommandUseCase;

    public ClientCommand(ObjectMapper mapper, ClientCommandUseCase clientCommandUseCase) {
        super(mapper);
        this.clientCommandUseCase = clientCommandUseCase;
    }

    @PostMapping("/client")
    public Mono<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {
        return clientCommandUseCase.saveClient(this.dtoToModel(clientDTO))
                .map(this::modelToDTO);
    }

}
