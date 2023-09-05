package com.softlond.model.exceptions;

import com.softlond.model.enums.GenericErrorEnum;

public class GenericException extends RuntimeException{
    public GenericException(GenericErrorEnum error) {
        super(error.name());
    }
}
