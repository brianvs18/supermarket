package com.softlond.model.exceptions;

import com.softlond.model.enums.CategoryErrorEnum;

public class CategoryException extends RuntimeException {
    public CategoryException(CategoryErrorEnum error) {
        super(error.name());
    }
}
