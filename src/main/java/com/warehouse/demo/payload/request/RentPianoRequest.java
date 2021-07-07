package com.warehouse.demo.payload.request;


import javax.validation.constraints.Min;
import java.sql.Date;
import java.time.LocalDate;

public class RentPianoRequest {

    private String sku;

    private LocalDate startDate;

    private LocalDate expirationDate;

    public RentPianoRequest() {
    }

    public RentPianoRequest(String sku, LocalDate startDate, LocalDate expirationDate) {
        this.sku = sku;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public String getSku() {
        return sku;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
