package com.rkt.app.advice;

import com.rkt.app.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotPresentException.class)
    public Map<String, String> handleUserNotPresentException(UserNotPresentException ex) {
        Map<String,String> errorMap = new HashMap<>();

        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MenuNotPresentException.class)
    public String handleUserNotPresentException(MenuNotPresentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleRequestBodyNotFoundException(HttpMessageNotReadableException ex) {
        return "Required request body is missing. Please provide request body.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public String handleEException(InsufficientAuthenticationException ex) {
        return "fffdfdfdfdffdf";
    }

}
