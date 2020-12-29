package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.FellNotFoundExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class FellNotFoundExceptionHandlerTest {

    private static final String NOW = "2012-21-05 12:01:32";
    private static final String FELL_RESOURCE_ID = "missing";

    private ResponseEntity<Object> fellNotFoundResponseEntity;

    @Before
    public void setUp() {
        FellNotFoundException fellNotFoundException = new FellNotFoundException(FELL_RESOURCE_ID, NOW);

        FellNotFoundExceptionHandler exceptionHandler = new FellNotFoundExceptionHandler();
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException);
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainStatusCode() {
        assertThat(((ErrorDTO) fellNotFoundResponseEntity.getBody()).getStatus(),
            is(equalTo(String.valueOf(HttpStatus.NOT_FOUND.value()))));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainMessage() {
        assertThat(((ErrorDTO) fellNotFoundResponseEntity.getBody()).getMessage(),
            is(equalTo("Fell was not found for {id=" + FELL_RESOURCE_ID + "}")));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainTimestamp() {
        assertThat(((ErrorDTO) fellNotFoundResponseEntity.getBody()).getTimestamp(),
            is(equalTo(NOW)));
    }

}
