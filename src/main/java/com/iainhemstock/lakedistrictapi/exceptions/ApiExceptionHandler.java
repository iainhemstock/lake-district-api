package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = FellNotFoundException.class)
    public ResponseEntity<Object> handleFellNotFoundException(FellNotFoundException ex) {

        ErrorDto errorDTO = new ErrorDto(
            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            String.format("/fells/%d", ex.getUnrecognizedId()),
            ex.getTimestamp());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMethodNotAllowedException.class)
    public ResponseEntity<Object> handleMethodNotAllowedException(HttpMethodNotAllowedException ex) {

        ErrorDto errorDTO = new ErrorDto(
            HttpStatus.METHOD_NOT_ALLOWED,
            ex.getMessage(),
            ex.getPath(),
            ex.getTimestamp());

        return new ResponseEntity<>(errorDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return "{\"status\"=\"BAD_REQUEST\"}";
    }
}
