package com.example.tp_rest.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}
