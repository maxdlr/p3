package com.p3.model;

import java.util.Date;

abstract public class ModelEntity {

    protected Date createdAt;

    protected Date updatedAt;

    protected Date getCreatedAt() {
        return createdAt;
    }

    protected Date getUpdatedAt() {
        return updatedAt;
    }
}
