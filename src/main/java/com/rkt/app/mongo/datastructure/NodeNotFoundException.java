package com.rkt.app.mongo.datastructure;

public class NodeNotFoundException extends RuntimeException{
    public NodeNotFoundException(String message) {
        super(message);
    }
}
