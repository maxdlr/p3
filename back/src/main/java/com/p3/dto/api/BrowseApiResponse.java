package com.p3.dto.api;

import com.p3.model.ModelEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class BrowseApiResponse<T extends ModelEntity> extends ApiResponse {
    public BrowseApiResponse(String key, Iterable<T> data) {

        HashMap<String, Iterable<T>> map = new HashMap<>();
        map.put(key, data);

        this.response = map;
        this.status = HttpStatus.OK;
    }
}
