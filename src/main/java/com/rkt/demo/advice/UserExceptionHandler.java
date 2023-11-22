package com.rkt.demo.advice;

import com.rkt.demo.exception.EmailAlreadyInUserException;
import com.rkt.demo.exception.TokenExpiresException;
import com.rkt.demo.exception.TokenNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyInUserException.class)
    public Map<String, String> handleUserAlreadyExistsException(EmailAlreadyInUserException ex) {
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenExpiresException.class)
    public Map<String, String> handleTokenExpiresException(TokenExpiresException ex) {
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenNotValidException.class)
    public Map<String, String> handleTokenNotValidException(TokenNotValidException ex) {
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }
}
