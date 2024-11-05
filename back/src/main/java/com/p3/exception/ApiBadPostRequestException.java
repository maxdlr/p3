package com.p3.exception;

public class ApiBadPostRequestException extends RuntimeException {
    public ApiBadPostRequestException(String message) {
        super(message);
    }
}
