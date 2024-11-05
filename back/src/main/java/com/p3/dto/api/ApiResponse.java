package com.p3.dto.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

abstract public class ApiResponse {

    protected HashMap<String, Object> response = new HashMap<>();
    protected HttpStatus status;

    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(
                this.response,
                this.status
        );
    }
}
