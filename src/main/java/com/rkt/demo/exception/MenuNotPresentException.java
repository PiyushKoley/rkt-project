package com.rkt.demo.exception;

public class MenuNotPresentException extends RuntimeException{
    public MenuNotPresentException(String message) {
        super(message);
    }
}
