package com.p3.exception;

public class ApiResourceNotFoundException extends RuntimeException {
    public ApiResourceNotFoundException(String message) {
        super(message);
    }
}
