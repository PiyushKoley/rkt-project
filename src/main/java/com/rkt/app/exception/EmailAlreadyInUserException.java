package com.rkt.app.exception;

public class EmailAlreadyInUserException extends RuntimeException{
    public EmailAlreadyInUserException(String message) {
        super(message);
    }
}
