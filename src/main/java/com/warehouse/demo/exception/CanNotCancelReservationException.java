package com.warehouse.demo.exception;

public class CanNotCancelReservationException extends RuntimeException {
    public CanNotCancelReservationException(String message) {
        super(message);
    }
}
