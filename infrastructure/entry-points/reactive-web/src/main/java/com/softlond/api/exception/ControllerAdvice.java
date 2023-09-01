package com.softlond.api.exception;

import com.softlond.api.dto.ErrorDTO;
import com.softlond.model.exceptions.CategoryException;
import com.softlond.model.exceptions.ClientException;
import com.softlond.model.exceptions.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<ErrorDTO> clientExceptionHandler(ClientException exception) {
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CategoryException.class)
    public ResponseEntity<ErrorDTO> categoryExceptionHandler(CategoryException exception) {
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ErrorDTO> categoryExceptionHandler(ProductException exception) {
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
