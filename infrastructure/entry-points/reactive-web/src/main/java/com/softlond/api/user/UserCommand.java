package com.softlond.api.user;

import com.softlond.usecase.userusecase.UserCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserCommand {

    private final UserCommandUseCase userCommandUseCase;
}
