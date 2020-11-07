package com.iainhemstock.lakedistrictapi.exceptions;

import org.springframework.http.HttpMethod;

public final class HttpMethodNotAllowedException extends RuntimeException {
    private String path;
    private String timestamp;
    private HttpMethod method;

    public HttpMethodNotAllowedException(String path, String timestamp, HttpMethod method) {
        super("Method '" + method.name() + "' is not allowed");
        this.path = path;
        this.timestamp = timestamp;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
