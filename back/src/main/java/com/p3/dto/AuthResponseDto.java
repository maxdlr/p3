package com.p3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDto {
    @JsonProperty("token")
    private String token;

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
