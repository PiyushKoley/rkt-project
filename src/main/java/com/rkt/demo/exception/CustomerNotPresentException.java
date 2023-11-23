package com.rkt.demo.exception;

public class CustomerNotPresentException extends RuntimeException{
    public CustomerNotPresentException(String message) {
        super(message);
    }
}
