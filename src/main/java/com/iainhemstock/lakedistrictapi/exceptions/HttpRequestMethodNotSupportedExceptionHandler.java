package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Component
public class HttpRequestMethodNotSupportedExceptionHandler {

    private final ApiClock apiClock;

    @Autowired
    public HttpRequestMethodNotSupportedExceptionHandler(final ApiClock apiClock) {
        this.apiClock = apiClock;
    }

    public ResponseEntity<Object> handleException(final HttpRequestMethodNotSupportedException ex, final String requestUrl) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

        ErrorDTO errorDto = new ErrorDTO(
            String.valueOf(status.value()),
            String.format("Method %s is not supported", ex.getMethod()),
            requestUrl,
            apiClock.now()
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ALLOW, HttpMethod.GET.name());
        return new ResponseEntity<>(errorDto, responseHeaders, status);
    }
}
