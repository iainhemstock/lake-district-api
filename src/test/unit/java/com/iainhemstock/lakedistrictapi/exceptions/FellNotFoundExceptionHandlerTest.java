package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class FellNotFoundExceptionHandlerTest {

    private static final String NOW = "2012-21-05 12:01:32";
    private static final int FELL_ID = 3;

    @Mock private WebRequest webRequest;

    private FellNotFoundExceptionHandler exceptionHandler;
    private ResponseEntity<Object> fellNotFoundResponseEntity;
    private FellNotFoundException fellNotFoundException;
    private HttpHeaders responseHeaders;

    @Before
    public void setUp() {
        responseHeaders = new HttpHeaders();
        responseHeaders.add("Allow", "GET");

        fellNotFoundException = new FellNotFoundException(
            FELL_ID,
            NOW,
            HttpMethod.GET.name(),
            String.format("http://localhost:8080/api/fells/%d", FELL_ID),
            responseHeaders);

        exceptionHandler = new FellNotFoundExceptionHandler();
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_responseHeaderWillIndicateWhichMethodsAreAllowed() {
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException, webRequest);
        assertThat(fellNotFoundResponseEntity.getHeaders().get("Allow"),
            is(equalTo(List.of("GET"))));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainStatusCode() {
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException, webRequest);
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getStatus(),
            is(equalTo(String.valueOf(HttpStatus.NOT_FOUND.value()))));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainMessage() {
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException, webRequest);
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getMessage(),
            is(equalTo("Fell was not found for {id=" + FELL_ID + "}")));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainPath() {
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException, webRequest);
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getPath(),
            is(equalTo("http://localhost:8080/api/fells/" + FELL_ID)));
    }

    @Test
    public void given_fellNotFoundExceptionHasBeenThrown_when_handled_then_errorResponseWillContainTimestamp() {
        fellNotFoundResponseEntity = exceptionHandler.handleException(fellNotFoundException, webRequest);
        assertThat(((ErrorDto) fellNotFoundResponseEntity.getBody()).getTimestamp(),
            is(equalTo(NOW)));
    }

}
