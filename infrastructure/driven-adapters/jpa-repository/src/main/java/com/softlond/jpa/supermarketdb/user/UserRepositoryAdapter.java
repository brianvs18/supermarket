package com.softlond.jpa.supermarketdb.user;

import com.softlond.jpa.helper.AdapterOperations;
import com.softlond.model.user.User;
import com.softlond.model.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class UserRepositoryAdapter extends AdapterOperations<User, UserEntity, String, JpaUserRepository> implements UserRepository {

    protected UserRepositoryAdapter(JpaUserRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, User.class));
    }

    @Override
    public Flux<User> findAllUsers() {
        return Flux.fromIterable(repository.findAllUsers())
                .map(this::toEntity);
    }

    @Override
    public Mono<User> saveUser(User user) {
        return Mono.justOrEmpty(user).map(this::toData)
                .map(repository::save)
                .map(this::toEntity);
    }
}
