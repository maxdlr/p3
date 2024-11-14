package com.p3.dto.api;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class EditApiResponse extends ApiResponse {
    public EditApiResponse(
            String message
    ) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);

        this.response = map;
        this.status = HttpStatus.OK;
    }
}
