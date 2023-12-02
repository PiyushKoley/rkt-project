package com.rkt.app.exception;

public class CustomerNotPresentException extends RuntimeException{
    public CustomerNotPresentException(String message) {
        super(message);
    }
}
