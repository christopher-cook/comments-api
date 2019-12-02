package com.example.exception;

import com.example.commentsapi.exception.ErrorResponse;
import com.example.commentsapi.exception.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.junit.Assert.assertEquals;

public class ExceptionHandlerTest {

    private ExceptionHandler exceptionHandler;
    private ResponseEntity<ErrorResponse> response;

    @Before
    public void init() {

        exceptionHandler = new ExceptionHandler();
        response = exceptionHandler.handleException(new RuntimeException("msg"));
    }

    @Test
    public void constructor_ResponseEntityErrorResponse() {

        assertEquals("msg", response.getBody().getMessage());
        assertEquals(400, response.getStatusCodeValue());
    }
}
