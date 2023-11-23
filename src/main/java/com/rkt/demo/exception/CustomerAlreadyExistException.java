package com.rkt.demo.exception;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
