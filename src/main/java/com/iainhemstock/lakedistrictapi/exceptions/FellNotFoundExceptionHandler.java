package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Component
public class FellNotFoundExceptionHandler {

    public ResponseEntity<Object> handleException(final FellNotFoundException ex) {
        ErrorDto errorDTO = new ErrorDto(
            String.valueOf(HttpURLConnection.HTTP_NOT_FOUND),
            ex.getMessage(),
            String.format("%s", ex.getRequestUrl()),
            ex.getTimestamp());

        return new ResponseEntity<>(errorDTO, ex.getHeaders(), HttpStatus.NOT_FOUND);
    }

}
