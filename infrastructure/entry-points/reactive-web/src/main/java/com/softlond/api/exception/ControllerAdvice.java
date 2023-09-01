package com.softlond.api.exception;

import com.softlond.api.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException exception) {
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).status(String.valueOf(HttpStatus.BAD_REQUEST)).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointerExceptionHandler(NullPointerException exception) {
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
