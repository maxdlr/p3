package com.p3.dto.api;

import org.springframework.http.HttpStatus;

public class AddApiResponse extends ApiResponse {
    public AddApiResponse(
            String message
    ) {
        this.response.put("message", message);
        this.status = HttpStatus.CREATED;
    }
}
