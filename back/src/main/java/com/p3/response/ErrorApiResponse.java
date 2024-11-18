package com.p3.response;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class ErrorApiResponse extends ApiResponse {
    public ErrorApiResponse(
            String message,
            HttpStatus status
    ) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);

        this.response = map;
        this.status = status;
    }
}
