package com.rkt.app.exception;

public class MenuNotPresentException extends RuntimeException{
    public MenuNotPresentException(String message) {
        super(message);
    }
}
