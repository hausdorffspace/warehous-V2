package com.warehouse.demo.model;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startReservationDate;

    private LocalDate endReservationDate;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "pianoId")
    private Piano piano;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", startReservationDate=" + startReservationDate +
                ", endReservationDate=" + endReservationDate +
                ", piano=" + piano +
                ", user=" + user +
                '}';
    }

    public Reservation() {
    }

    public Reservation(Long id, LocalDate startReservationDate, LocalDate endReservationDate, Piano piano, User user) {
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
