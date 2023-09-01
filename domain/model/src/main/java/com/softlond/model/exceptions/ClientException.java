package com.softlond.model.exceptions;

import com.softlond.model.enums.ClientErrorEnum;

public class ClientException extends RuntimeException {
    public ClientException(ClientErrorEnum error) {
        super(error.name());
    }
}
