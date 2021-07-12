package com.warehouse.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
public class Piano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StainwayModel model;

    @Enumerated(EnumType.STRING)
    private Producer producer;

    private Boolean availableNow;

    @OneToMany(mappedBy = "piano")
    private Set<Reservation> reservationSet;

    private Integer price;

    private String SKU;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StainwayModel getModel() {
        return model;
    }

    public void setModel(StainwayModel model) {
        this.model = model;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Boolean getAvaliable() {
        return availableNow;
    }

    public void setAvaliable(Boolean availableNow) {
        this.availableNow = availableNow;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
}
