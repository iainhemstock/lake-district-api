package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
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
        FellNotFoundException fellNotFoundException = new FellNotFoundException(
            FELL_RESOURCE_ID,
            NOW,
            HttpMethod.GET.name(),
            String.format("http://localhost:8080/api/fells/%s", FELL_RESOURCE_ID));

        FellNotFoundExceptionHandler exceptionHandler = new FellNotFoundExceptionHandler();
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException);
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainStatusCode() {
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getStatus(),
            is(equalTo(String.valueOf(HttpStatus.NOT_FOUND.value()))));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainMessage() {
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getMessage(),
            is(equalTo("Fell was not found for {id=" + FELL_RESOURCE_ID + "}")));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainPath() {
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getPath(),
            is(equalTo("http://localhost:8080/api/fells/" + FELL_RESOURCE_ID)));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainTimestamp() {
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getTimestamp(),
            is(equalTo(NOW)));
    }

}
