package com.api.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        if(e.getMessage().contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        if(e.getMessage().contains("exists")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
