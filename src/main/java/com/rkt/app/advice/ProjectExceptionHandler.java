package com.rkt.app.advice;

import com.rkt.app.exception.ProjectNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProjectNotPresentException.class)
    public String handleProjectNotPresentException(ProjectNotPresentException ex) {
        return ex.getMessage();
    }
}
