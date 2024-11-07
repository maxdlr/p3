package com.p3.dto.api;

import org.springframework.http.HttpStatus;

public class ErrorApiResponse extends ApiResponse {
    public ErrorApiResponse(
            String message,
            HttpStatus status
    ) {
        this.response.put("error", message);
        this.status = status;
    }
}
