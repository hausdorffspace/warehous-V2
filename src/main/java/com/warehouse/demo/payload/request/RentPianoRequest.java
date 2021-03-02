package com.warehouse.demo.payload.request;


import javax.validation.constraints.Min;

public class RentPianoRequest {

    private String sku;

    @Min(10)
    private Long periodInDay;

    public RentPianoRequest() {
    }

    public RentPianoRequest(String sku, Long periodInDay) {
        this.sku = sku;
        this.periodInDay = periodInDay;
    }

    public Long getPeriodInDay() {
        return periodInDay;
    }

    public void setPeriodInDay(Long periodInDay) {
        this.periodInDay = periodInDay;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
