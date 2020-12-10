package com.warehouse.demo.exception;

public class PianoNotFoundException extends RuntimeException {
    public PianoNotFoundException(String sku) {
        super("can not find piano with sku: " + sku);
    }
}
