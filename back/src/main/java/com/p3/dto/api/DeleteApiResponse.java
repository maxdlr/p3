package com.p3.dto.api;

import org.springframework.http.HttpStatus;

public class DeleteApiResponse extends ApiResponse {
    public DeleteApiResponse(
            String message
    ) {
        this.response.put("message", message);
        this.status = HttpStatus.OK;
    }
}
