package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ErrorDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.HttpRequestMethodNotSupportedExceptionHandler;
import com.iainhemstock.lakedistrictapi.application_logic.ApiClockServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class HttpRequestMethodNotSupportedExceptionHandlerTest {

    private static final int FELL_ID = 3;
    private static final String NOW = "2012-21-05 12:01:32";

    @Mock private ApiClockServiceImpl apiClockService;

    private ResponseEntity<Object> methodNotAllowedResponseEntity;
    private TestApiProperties apiProperties;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();

        Mockito.when(apiClockService.now())
            .thenReturn(NOW);

        HttpRequestMethodNotSupportedException methodNotAllowedException = new HttpRequestMethodNotSupportedException(
            HttpMethod.POST.name(),
            "Method POST is not supported");

        HttpRequestMethodNotSupportedExceptionHandler exceptionHandler = new HttpRequestMethodNotSupportedExceptionHandler(apiClockService);
        methodNotAllowedResponseEntity = exceptionHandler.handleException(methodNotAllowedException, apiProperties.getBaseUrl() + "/fells/" + FELL_ID);
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_responseHeaderWillIndicateWhichMethodsAreAllowed() {
        assertThat(
            methodNotAllowedResponseEntity.getHeaders().get("Allow"),
            is(equalTo(List.of("GET"))));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainStatus() {
        assertThat(
            ((ErrorDTO) methodNotAllowedResponseEntity.getBody()).getStatus(),
            is(equalTo(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainMessage() {
        assertThat(
            ((ErrorDTO) methodNotAllowedResponseEntity.getBody()).getMessage(),
            is(equalTo("Method POST is not supported")));
    }

    @Test
    public void given_httpMethodNotAllowedExceptionHasBeenThrown_when_handled_then_errorResponseWillContainTimestamp() {
        assertThat(
            ((ErrorDTO) methodNotAllowedResponseEntity.getBody()).getTimestamp(),
            is(equalTo(NOW)));
    }

}