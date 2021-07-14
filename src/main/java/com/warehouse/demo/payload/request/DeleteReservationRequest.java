package com.warehouse.demo.payload.request;

import java.io.Serializable;

public class DeleteReservationRequest implements Serializable {
    public Long reservationId;

    public DeleteReservationRequest(Long reservationId) {
        this.reservationId = reservationId;
    }

    public DeleteReservationRequest() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
