package com.marin.app.mr.exception;

import com.marin.app.mr.ui.model.response.Error.ErrorMessageResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler {

    /**
     * Method for handling MeterServiceException and providing custom response.
     *
     * @param ex    MeterServiceException
     * @param request   WebRequest
     * @return  The Response containing the custom error model and, http status.
     */
    @ExceptionHandler(value = {MeterServiceException.class})
    public ResponseEntity<Object> handleMeterServiceException(MeterServiceException ex, WebRequest request) {

        ErrorMessageResponseModel errorModel = new ErrorMessageResponseModel(LocalDateTime.now(), ex.getMessage());

        return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
