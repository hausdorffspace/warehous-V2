package com.warehouse.demo.exception;

import com.warehouse.demo.exception.bodyResponse.ResponseBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CannotReservePianoInThisDurationExceptionAdvice {

    @ExceptionHandler(CannotReservePianoInThisDurationException.class)
    public ResponseEntity<?> tokenNotFoundException(CannotReservePianoInThisDurationException e){
        ResponseBodyException response = new ResponseBodyException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
