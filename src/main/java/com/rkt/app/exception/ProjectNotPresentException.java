package com.rkt.app.exception;

public class ProjectNotPresentException extends RuntimeException{
    public ProjectNotPresentException(String message) {
        super(message);
    }
}
