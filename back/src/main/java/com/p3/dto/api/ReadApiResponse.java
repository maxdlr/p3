package com.p3.dto.api;

import com.p3.model.ModelEntity;
import org.springframework.http.HttpStatus;

public class ReadApiResponse<T extends ModelEntity> extends ApiResponse {
    public ReadApiResponse(
            T data
    ) {
        this.status = HttpStatus.OK;
    }
}
