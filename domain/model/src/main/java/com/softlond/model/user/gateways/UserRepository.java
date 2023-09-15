package com.softlond.model.user.gateways;

import com.softlond.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Flux<User> findAllUsers();

    Mono<User> saveUser(User user);
}
