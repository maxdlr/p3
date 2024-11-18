package com.p3.response;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class AddApiResponse extends ApiResponse {
    public AddApiResponse(
            String message
    ) {
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", message);

        this.response = responseMap;
        this.status = HttpStatus.CREATED;
    }
}
