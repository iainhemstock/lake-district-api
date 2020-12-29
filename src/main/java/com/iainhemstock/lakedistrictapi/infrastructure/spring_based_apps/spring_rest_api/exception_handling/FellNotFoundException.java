package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

public class FellNotFoundException extends RuntimeException {
    private final String resourceId;
    private final String timestamp;

    public FellNotFoundException(final String resourceId, final String timestamp) {
        this.resourceId = resourceId;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return String.format("Fell was not found for {os map ref=%s}", resourceId);
    }

    public String getTimestamp() {
        return timestamp;
    }

}
