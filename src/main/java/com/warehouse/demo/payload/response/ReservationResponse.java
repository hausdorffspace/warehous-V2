package com.warehouse.demo.payload.response;

import com.warehouse.demo.model.*;
import lombok.Builder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
public class ReservationResponse implements Serializable {

    private Long id;

    private LocalDate startReservationDate;

    private LocalDate endReservationDate;

    private StainwayModel model;

    private Producer producer;

    private Integer price;

    private String SKU;

    private String name;

    private String username;

    private String email;

    public ReservationResponse() {
    }

    public ReservationResponse(Long id, LocalDate startReservationDate, LocalDate endReservationDate, StainwayModel model, Producer producer, Integer price, String SKU, String name, String username, String email) {
        this.id = id;
        this.startReservationDate = startReservationDate;
        this.endReservationDate = endReservationDate;
        this.model = model;
        this.producer = producer;
        this.price = price;
        this.SKU = SKU;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartReservationDate() {
        return startReservationDate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public StainwayModel getModel() {
        return model;
    }

    public Producer getProducer() {
        return producer;
    }

    public Integer getPrice() {
        return price;
    }

    public String getSKU() {
        return SKU;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
