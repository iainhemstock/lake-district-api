package com.iainhemstock.lakedistrictapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.NoHandlerFoundException;

public class FellNotFoundException extends NoHandlerFoundException {
    private int fellId;
    private String timestamp;

    public FellNotFoundException(int fellId,
                                 String timestamp,
                                 String httpMethod,
                                 String requestUrl,
                                 HttpHeaders headers) {
        super(httpMethod, requestUrl, headers);
        this.fellId = fellId;
        this.timestamp = timestamp;
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
}
