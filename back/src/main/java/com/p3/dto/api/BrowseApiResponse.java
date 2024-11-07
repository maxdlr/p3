package com.p3.dto.api;

import com.p3.model.ModelEntity;
import org.springframework.http.HttpStatus;

public class BrowseApiResponse<T extends ModelEntity> extends ApiResponse {
    public BrowseApiResponse(String key, Iterable<T> data) {
        this.response.put(key, data);
        this.status = HttpStatus.OK;
    }
}
