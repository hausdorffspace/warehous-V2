package com.warehouse.demo.util;

import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.payload.response.ReservationResponse;
import org.springframework.stereotype.Component;

@Component
public class MapReservationToReservationResponse {

    public ReservationResponse map(Reservation reservation){
        return ReservationResponse.builder()
                .id(reservation.getId())
                .startReservationDate(reservation.getStartReservationDate())
                .endReservationDate(reservation.getEndReservationDate())
                .model(reservation.getPiano().getModel())
                .producer(reservation.getPiano().getProducer())
                .price(reservation.getPiano().getPrice())
                .SKU(reservation.getPiano().getSKU())
                .name(reservation.getUser().getName())
                .username(reservation.getUser().getUsername())
                .email(reservation.getUser().getEmail())
                .build();
    }
}
