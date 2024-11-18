package com.p3.response;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class RegisterApiResponse extends ApiResponse {
    public RegisterApiResponse(
            String message,
            String token
    ) {
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", message);
        responseMap.put("token", token);

        this.response = responseMap;
        this.status = HttpStatus.CREATED;
    }
}
