package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import com.iainhemstock.lakedistrictapi.services.ApiClock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class HttpRequestMethodNotSupportedExceptionHandlerTest {

    private static final int FELL_ID = 3;
    private static final String NOW = "2012-21-05 12:01:32";
    private static final String REQUEST_URL = TestApiProperties.API_BASE_URL + "/fells/" + FELL_ID;

    @Mock private ApiProperties apiProperties;
    @Mock private ApiClock apiClock;

    private HttpRequestMethodNotSupportedExceptionHandler exceptionHandler;
    private ResponseEntity<Object> methodNotAllowedResponseEntity;
    private HttpRequestMethodNotSupportedException methodNotAllowedException;

    @Before
    public void setUp() {
        Mockito.when(apiClock.now())
            .thenReturn(NOW);

        methodNotAllowedException = new HttpRequestMethodNotSupportedException(
            "POST",
            "Method POST is not supported");

        exceptionHandler = new HttpRequestMethodNotSupportedExceptionHandler(apiProperties, apiClock);
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_responseHeaderWillIndicateWhichMethodsAreAllowed() {
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, REQUEST_URL);
        assertThat(methodNotAllowedResponseEntity.getHeaders().get("Allow"),
            is(equalTo(List.of("GET"))));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainStatus() {
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, REQUEST_URL);
        assertThat(((ErrorDto) methodNotAllowedResponseEntity.getBody()).getStatus(),
            is(equalTo(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainMessage() {
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, REQUEST_URL);
        assertThat(((ErrorDto) methodNotAllowedResponseEntity.getBody()).getMessage(),
            is(equalTo("Method POST is not supported")));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainPath() {
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, REQUEST_URL);
        assertThat(((ErrorDto) methodNotAllowedResponseEntity.getBody()).getPath(),
            is(equalTo(TestApiProperties.API_BASE_URL + "/fells/" + FELL_ID)));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainTimestamp() {
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, REQUEST_URL);
        assertThat(((ErrorDto) methodNotAllowedResponseEntity.getBody()).getTimestamp(),
            is(equalTo(NOW)));
    }

}
