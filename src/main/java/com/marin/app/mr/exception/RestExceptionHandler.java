package com.marin.app.mr.exception;

import com.marin.app.mr.ui.model.response.Error.ErrorMessageResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method for handling message not readable exception.
     *
     * @param ex    HttpMessageNotReadableException
     * @param headers   HttpHeaders
     * @param status    HttpStatus
     * @param request   WebRequest
     * @return The Response containing the custom error model and, http status.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ErrorMessageResponseModel errorModel = new ErrorMessageResponseModel(LocalDateTime.now(), ex.getMessage());

        return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

}
