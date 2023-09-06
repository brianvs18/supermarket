package com.system.model.exceptions;

import com.system.model.enums.CategoryErrorEnum;

public class CategoryException extends RuntimeException {
    public CategoryException(CategoryErrorEnum error) {
        super(error.name());
    }
}
