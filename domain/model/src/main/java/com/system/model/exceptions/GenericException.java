package com.system.model.exceptions;

import com.system.model.enums.GenericErrorEnum;

public class GenericException extends RuntimeException{
    public GenericException(GenericErrorEnum error) {
        super(error.name());
    }
}
