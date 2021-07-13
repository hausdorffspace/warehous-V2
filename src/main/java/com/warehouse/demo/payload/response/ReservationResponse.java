package com.warehouse.demo.payload.response;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class ReservationResponse implements Serializable {

    private Long id;

    private LocalDate startReservationDate;

    private LocalDate endReservationDate;

    private Piano piano;

    private User user;

    public ReservationResponse() {
    }

    public ReservationResponse(Long id, LocalDate startReservationDate, LocalDate endReservationDate, Piano piano, User user) {
        this.id = id;
        this.startReservationDate = startReservationDate;
        this.endReservationDate = endReservationDate;
        this.piano = piano;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartReservationDate() {
        return startReservationDate;
    }

    public void setStartReservationDate(LocalDate startReservationDate) {
        this.startReservationDate = startReservationDate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public void setEndReservationDate(LocalDate endReservationDate) {
        this.endReservationDate = endReservationDate;
    }

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
