package com.iainhemstock.lakedistrictapi.exceptions;

public class FellNotFoundException extends RuntimeException {
    private final String resourceId;
    private final String timestamp;
    private final String httpMethod;
    private final String requestUrl;

    public FellNotFoundException(String resourceId,
                                 String timestamp,
                                 String httpMethod,
                                 String requestUrl) {
        this.resourceId = resourceId;
        this.timestamp = timestamp;
        this.httpMethod = httpMethod;
        this.requestUrl = requestUrl;
    }

    @Override
    public String getMessage() {
        return String.format("Fell was not found for {id=%s}", resourceId);
    }

    public String getUnrecognizedId() {
        return this.resourceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
