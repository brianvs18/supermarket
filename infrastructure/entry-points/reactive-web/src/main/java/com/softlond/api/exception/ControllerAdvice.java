package com.softlond.api.exception;

import com.softlond.api.dto.ErrorDTO;
import com.softlond.model.exceptions.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<ErrorDTO> clientExceptionHandler(ClientException exception) {
        ErrorDTO errorDTO = ErrorDTO.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
