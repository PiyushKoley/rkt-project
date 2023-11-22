package com.rkt.demo.exception;

public class EmailAlreadyInUserException extends RuntimeException{
    public EmailAlreadyInUserException(String message) {
        super(message);
    }
}
