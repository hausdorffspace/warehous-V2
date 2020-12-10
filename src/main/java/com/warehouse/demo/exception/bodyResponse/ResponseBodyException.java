package com.warehouse.demo.exception.bodyResponse;


import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ResponseBodyException {

    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

    public ResponseBodyException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
