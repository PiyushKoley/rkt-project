package com.rkt.app.mongo.datastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NodeExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NodeNotFoundException.class)
    public String nodeExceptionHandler(NodeNotFoundException ex) {
        return ex.getMessage();
    }
}
