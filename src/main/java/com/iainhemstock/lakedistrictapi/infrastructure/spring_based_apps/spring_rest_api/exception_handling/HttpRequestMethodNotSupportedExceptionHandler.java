package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.application_logic.ApiClockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Component
public class HttpRequestMethodNotSupportedExceptionHandler {

    private final ApiClockServiceImpl apiClockService;

    @Autowired
    public HttpRequestMethodNotSupportedExceptionHandler(final ApiClockServiceImpl apiClockService) {
        this.apiClockService = apiClockService;
    }

    public ResponseEntity<Object> handleException(final HttpRequestMethodNotSupportedException ex, final String requestUrl) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

        ErrorDTO errorDto = new ErrorDTO(
            String.format("%d %s", status.value(), status.name()),
            String.format("Method %s is not supported", ex.getMethod()),
            apiClockService.now()
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ALLOW, HttpMethod.GET.name());
        return new ResponseEntity<>(errorDto, responseHeaders, status);
    }
}
