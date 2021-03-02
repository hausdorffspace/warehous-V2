package com.warehouse.demo.exception;

public class PianoIsRentException extends RuntimeException{
    public PianoIsRentException(String message) {
        super("piano with sku: "+message + "is rent, pick another one");
    }
}
