package com.rkt.demo.exception;

public class TokenExpiresException extends RuntimeException{
    public TokenExpiresException(String message) {
        super(message);
    }
}
