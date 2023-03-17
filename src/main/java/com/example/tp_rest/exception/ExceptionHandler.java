package com.example.tp_rest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> userNotFound(BookNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
