package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
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
            ex.getTimestamp());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
