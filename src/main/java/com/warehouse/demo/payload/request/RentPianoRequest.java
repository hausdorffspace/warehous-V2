package com.warehouse.demo.payload.request;


public class RentPianoRequest {

    private String sku;

    public RentPianoRequest() {
    }

    public RentPianoRequest(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
