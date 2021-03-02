package com.warehouse.demo.exception;


import com.warehouse.demo.exception.bodyResponse.ResponseBodyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class PianoIsRentExceptionAdvice {

    @ExceptionHandler(PianoIsRentException.class)
    public ResponseEntity<?> pianoIsRentException(PianoIsRentException ex){
        ResponseBodyException response = new ResponseBodyException(
                ex.getMessage(),
                ex,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
