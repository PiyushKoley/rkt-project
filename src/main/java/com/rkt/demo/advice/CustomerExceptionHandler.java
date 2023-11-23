package com.rkt.demo.advice;

import com.rkt.demo.exception.CustomerAlreadyExistException;
import com.rkt.demo.exception.CustomerNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerNotPresentException.class)
    public String handleCustomerNotPresentException(CustomerNotPresentException ex) {
        return ex.getMessage();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public String handleCustomerAlreadyExistException(CustomerAlreadyExistException ex) {
        return ex.getMessage();
    }
}
