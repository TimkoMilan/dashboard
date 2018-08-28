package com.globallogic.dashboard.common;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.globallogic.dashboard.security.SecurityException;

import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SecurityException.class})
    public ResponseEntity<Object> handleSecurityError(
            SecurityException ex, WebRequest request) {
        ApiResponse responseBody = new ApiResponse(false, ex.getMessage());
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handleServiceError(
            ServiceException ex, WebRequest request) {
        return getBasicErrorResponse(ex,request,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getBasicErrorResponse(ex,request,HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> getBasicErrorResponse(Exception ex,
                                                         WebRequest request, HttpStatus status){
        ApiResponse responseBody = new ApiResponse(false, ex.getMessage());
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), status, request);
    }

}
