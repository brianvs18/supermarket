package com.softlond.model.exceptions;

import com.softlond.model.enums.SaleErrorEnum;

public class SaleException extends RuntimeException {
    public SaleException(SaleErrorEnum error) {
        super(error.name());
    }
}
