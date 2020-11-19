package com.iainhemstock.lakedistrictapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface ApiExceptionHandler {
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest);
}
