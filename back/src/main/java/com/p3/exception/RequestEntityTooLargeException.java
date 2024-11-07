package com.p3.exception;

public class RequestEntityTooLargeException extends RuntimeException {
    public RequestEntityTooLargeException(String message) {
        super(message);
    }
}
