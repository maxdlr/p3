package com.p3.dto.api;

import org.springframework.http.HttpStatus;

public class EditApiResponse extends ApiResponse {
    public EditApiResponse(
            String message
    ) {
        this.response.put("message", message);
        this.status = HttpStatus.OK;
    }
}
