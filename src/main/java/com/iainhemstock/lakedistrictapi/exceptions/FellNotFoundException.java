package com.iainhemstock.lakedistrictapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.NoHandlerFoundException;

public class FellNotFoundException extends RuntimeException {
    private int fellId;
    private String timestamp;
    private String httpMethod;
    private String requestUrl;
    private HttpHeaders headers;

    public FellNotFoundException(int fellId,
                                 String timestamp,
                                 String httpMethod,
                                 String requestUrl,
                                 HttpHeaders headers) {
        this.fellId = fellId;
        this.timestamp = timestamp;
        this.httpMethod = httpMethod;
        this.requestUrl = requestUrl;
        this.headers = headers;
    }

    @Override
    public String getMessage() {
        return String.format("Fell was not found for {id=%d}", fellId);
    }

    public int getUnrecognizedId() {
        return this.fellId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }
}
