package com.rkt.demo.exception;

public class ProjectNotPresentException extends RuntimeException{
    public ProjectNotPresentException(String message) {
        super(message);
    }
}
