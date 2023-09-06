package com.system.model.exceptions;

import com.system.model.enums.ClientErrorEnum;

public class ClientException extends RuntimeException {
    public ClientException(ClientErrorEnum error) {
        super(error.name());
    }
}
