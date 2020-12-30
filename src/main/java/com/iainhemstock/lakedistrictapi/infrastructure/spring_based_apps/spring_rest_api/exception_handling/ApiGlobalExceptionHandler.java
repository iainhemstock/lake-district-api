package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.exceptions.FellNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiGlobalExceptionHandler {

    @Autowired private FellNotFoundExceptionHandler fellNotFoundExceptionHandler;
    @Autowired private HttpRequestMethodNotSupportedExceptionHandler methodNotSupportedExceptionHandler;

    @ExceptionHandler({ FellNotFoundException.class, HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest httpServletRequest) {

        if (ex instanceof FellNotFoundException)
            return fellNotFoundExceptionHandler.handleException((FellNotFoundException) ex);
        else if (ex instanceof HttpRequestMethodNotSupportedException)
            return methodNotSupportedExceptionHandler.handleException(
                (HttpRequestMethodNotSupportedException) ex,
                httpServletRequest.getRequestURL().toString());
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
