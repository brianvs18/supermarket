package com.system.model.exceptions;

import com.system.model.enums.ProductErrorEnum;

public class ProductException extends RuntimeException{
    public ProductException(ProductErrorEnum error) {
        super(error.name());
    }
}
