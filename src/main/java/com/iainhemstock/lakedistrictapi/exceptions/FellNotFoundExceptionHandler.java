package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Component
public class FellNotFoundExceptionHandler {

    public ResponseEntity<Object> handleException(final FellNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO(
            String.valueOf(HttpURLConnection.HTTP_NOT_FOUND),
            ex.getMessage(),
            String.format("%s", ex.getRequestUrl()),
            ex.getTimestamp());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
