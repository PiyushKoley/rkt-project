package com.rkt.demo.exception;

public class UserNotPresentException extends RuntimeException{
    public UserNotPresentException(String message) {
        super(message);
    }
}
