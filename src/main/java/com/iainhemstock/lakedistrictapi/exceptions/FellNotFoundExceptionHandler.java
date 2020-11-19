package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.net.HttpURLConnection;

@Component
public class FellNotFoundExceptionHandler implements ApiExceptionHandler {

    @Override
    public ResponseEntity<Object> handleException(Exception ex, final WebRequest webRequest) {
        FellNotFoundException theEx = (FellNotFoundException) ex;

        ErrorDto errorDTO = new ErrorDto(
            String.valueOf(HttpURLConnection.HTTP_NOT_FOUND),
            ex.getMessage(),
            String.format("%s", theEx.getRequestURL()),
            theEx.getTimestamp());

        return new ResponseEntity<>(errorDTO, theEx.getHeaders(), HttpStatus.NOT_FOUND);
    }

}
