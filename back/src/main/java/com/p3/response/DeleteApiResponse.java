package com.p3.response;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class DeleteApiResponse extends ApiResponse {
    public DeleteApiResponse(
            String message
    ) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message",message);

        this.response = map;
        this.status = HttpStatus.OK;
    }
}
