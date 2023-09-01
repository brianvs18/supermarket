package com.softlond.model.exceptions;

import com.softlond.model.enums.ProductErrorEnum;

public class ProductException extends RuntimeException{
    public ProductException(ProductErrorEnum error) {
        super(error.name());
    }
}
