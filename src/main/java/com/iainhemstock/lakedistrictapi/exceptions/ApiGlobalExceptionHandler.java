package com.iainhemstock.lakedistrictapi.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApiGlobalExceptionHandler {

    @Autowired private FellNotFoundExceptionHandler fellNotFoundExceptionHandler;
    @Autowired private HttpRequestMethodNotSupportedExceptionHandler methodNotSupportedExceptionHandler;

    @ExceptionHandler({ FellNotFoundException.class, HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest) {
        if (ex instanceof FellNotFoundException)
            return fellNotFoundExceptionHandler.handleException(ex, webRequest);
        else if (ex instanceof HttpRequestMethodNotSupportedException)
            return methodNotSupportedExceptionHandler.handleException(ex, webRequest);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
