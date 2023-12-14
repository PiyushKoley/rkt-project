package com.rkt.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckConnection {

    @GetMapping("/check")
    public ResponseEntity<?> checkConnection() {
        return ResponseEntity.ok("up and connected");
    }
}
