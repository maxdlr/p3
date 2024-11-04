package com.p3.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        String errorCategory = categorizeError(authException.getMessage());

        switch (errorCategory) {
            case "expired":
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired. Please login again.");
                break;
            case "invalid":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token.");
                break;
            case "forbidden":
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access to this resource is forbidden.");
                break;
            case "not found":
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found.");
                break;
            case "conflict":
                response.sendError(HttpServletResponse.SC_CONFLICT, "Request conflict detected.");
                break;
            case "unsupported media":
                response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported media type.");
                break;
            case "method not allowed":
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed for this endpoint.");
                break;
            case "precondition failed":
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Precondition failed.");
                break;
            case "request entity too large":
                response.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "The request entity is too large.");
                break;
            case "internal server error":
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
                break;
            default:
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: " + authException.getMessage());
        }
    }

    private String categorizeError(String message) {
        if (message.contains("expired")) {
            return "expired";
        } else if (message.contains("invalid")) {
            return "invalid";
        } else if (message.contains("forbidden")) {
            return "forbidden";
        } else if (message.contains("not found")) {
            return "not found";
        } else if (message.contains("conflict")) {
            return "conflict";
        } else if (message.contains("unsupported media")) {
            return "unsupported media";
        } else if (message.contains("method not allowed")) {
            return "method not allowed";
        } else if (message.contains("precondition failed")) {
            return "precondition failed";
        } else if (message.contains("request entity too large")) {
            return "request entity too large";
        } else if (message.contains("too many requests")) {
            return "too many requests";
        } else if (message.contains("internal server error")) {
            return "internal server error";
        } else {
            return "unknown";
        }
    }
}
