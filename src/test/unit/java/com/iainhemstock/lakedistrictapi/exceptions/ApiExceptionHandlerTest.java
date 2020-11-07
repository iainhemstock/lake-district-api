package com.iainhemstock.lakedistrictapi.exceptions;

import com.iainhemstock.lakedistrictapi.controllers.FellController;
import com.iainhemstock.lakedistrictapi.dtos.ErrorDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiExceptionHandlerTest {

    private static final String NOW = "21-05-2012 12:01:32";
    private static final int FELL_ID = 3;

    private ApiExceptionHandler exceptionHandler;
    private ResponseEntity<Object> fellNotFoundResponse;
    private ErrorDTO fellNotFoundErrorDTO;
    private ResponseEntity<Object> httpMethodNotAllowedResponse;
    private ErrorDTO httpMethodNotAllowedErrorDTO;

    @Before
    public void setUp() throws Exception {
        exceptionHandler = new ApiExceptionHandler();

        fellNotFoundResponse = exceptionHandler.handleFellNotFoundException(
            new FellNotFoundException(FELL_ID, NOW));
        fellNotFoundErrorDTO = (ErrorDTO) fellNotFoundResponse.getBody();

        httpMethodNotAllowedResponse = exceptionHandler.handleMethodNotAllowedException(
            new HttpMethodNotAllowedException("/fells/" + FELL_ID, NOW, HttpMethod.POST));
        httpMethodNotAllowedErrorDTO = (ErrorDTO) httpMethodNotAllowedResponse.getBody();

    }

    @Test
    public void given_fell_not_found_exception_has_been_thrown_when_handled_then_error_response_will_contain_status() {
        assertThat(fellNotFoundErrorDTO.getStatus(),
            is(equalTo(HttpStatus.NOT_FOUND)));
    }

    @Test
    public void given_fell_not_found_exception_has_been_thrown_when_handled_then_error_response_will_contain_message() {
        assertThat(fellNotFoundErrorDTO.getMessage(),
            is(equalTo("Fell was not found for {id=" + FELL_ID + "}")));
    }

    @Test
    public void given_fell_not_found_exception_has_been_thrown_when_handled_then_error_response_will_contain_path() {
        assertThat(fellNotFoundErrorDTO.getPath(),
            is(equalTo("/fells/" + FELL_ID)));
    }

    @Test
    public void given_fell_not_found_exception_has_been_thrown_when_handled_then_error_response_will_contain_timestamp() {
        assertThat(fellNotFoundErrorDTO.getTimestamp(),
            is(equalTo(NOW)));
    }

    @Test
    public void given_http_method_not_allowed_exception_has_been_thrown_when_handled_then_error_response_will_contain_status() {
        assertThat(httpMethodNotAllowedErrorDTO.getStatus(),
            is(equalTo(HttpStatus.METHOD_NOT_ALLOWED)));
    }

    @Test
    public void given_http_method_not_allowed_exception_has_been_thrown_when_handled_then_error_response_will_contain_message() {
        assertThat(httpMethodNotAllowedErrorDTO.getMessage(),
            is(equalTo("Method 'POST' is not allowed")));
    }

    @Test
    public void given_http_method_not_allowed_exception_has_been_thrown_when_handled_then_error_response_will_contain_path() {
        assertThat(httpMethodNotAllowedErrorDTO.getPath(),
            is(equalTo("/fells/" + FELL_ID)));
    }

    @Test
    public void given_http_method_not_allowed_exception_has_been_thrown_when_handled_then_error_response_will_contain_timestamp() {
        assertThat(httpMethodNotAllowedErrorDTO.getTimestamp(),
            is(equalTo(NOW)));
    }

    @Test
    public void given_method_argument_type_mismatch_exception_has_been_thrown_when_handled_then_error_response_will_contain_status() throws NoSuchMethodException {
        String getFell = exceptionHandler.handleMethodArgumentTypeMismatchException(
            new MethodArgumentTypeMismatchException(
                null, null, "",
                new MethodParameter(FellController.class.getDeclaredMethod("getFell", int.class), 0),
                new NumberFormatException("")));
//        ErrorDTO errorDTO errorDTO = exceptionHandler.handleMethodArgumentTypeMismatchException(
//            new MethodArgumentTypeMismatchException(
//                null, null, "",
//                new MethodParameter(FellController.class.getDeclaredMethod("getFell", int.class), 0),
//                new NumberFormatException("")));
//
//        assertThat(response, is("{\"status\"=\"BAD_REQUEST\"}"));
    }
}