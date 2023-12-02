package com.rkt.app.exception;

public class TokenExpiresException extends RuntimeException{
    public TokenExpiresException(String message) {
        super(message);
    }
}
