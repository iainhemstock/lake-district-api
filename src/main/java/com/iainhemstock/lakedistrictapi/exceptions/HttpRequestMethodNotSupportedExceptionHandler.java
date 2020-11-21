package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
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

    private ApiProperties apiProperties;
    private ApiClock apiClock;

    @Autowired
    public HttpRequestMethodNotSupportedExceptionHandler(final ApiProperties apiProperties, final ApiClock apiClock) {
        this.apiProperties = apiProperties;
        this.apiClock = apiClock;
    }

    public ResponseEntity<Object> handleException(final HttpRequestMethodNotSupportedException ex, final String requestUrl) {
        HttpStatus myStatus = HttpStatus.METHOD_NOT_ALLOWED;

        ErrorDto errorDto = new ErrorDto(
            String.valueOf(myStatus.value()),
            String.format("Method %s is not supported", ex.getMethod()),
            requestUrl,
            apiClock.now()
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ALLOW, HttpMethod.GET.name());
        return new ResponseEntity<>(errorDto, responseHeaders, myStatus);
    }
}
