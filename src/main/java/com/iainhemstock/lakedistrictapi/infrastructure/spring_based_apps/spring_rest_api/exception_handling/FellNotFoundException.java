package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

public class FellNotFoundException extends RuntimeException {
    private final String resourceId;
    private final String timestamp;
    private final String httpMethod;

    public FellNotFoundException(final String resourceId, final String timestamp, final String httpMethod) {
        this.resourceId = resourceId;
        this.timestamp = timestamp;
        this.httpMethod = httpMethod;
    }

    @Override
    public String getMessage() {
        return String.format("Fell was not found for {id=%s}", resourceId);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }
}
