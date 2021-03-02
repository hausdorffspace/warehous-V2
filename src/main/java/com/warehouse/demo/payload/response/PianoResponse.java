package com.warehouse.demo.payload.response;

import java.io.Serializable;

public class PianoResponse implements Serializable {

    private Long id;

    private String model;

    private String producer;

    private Boolean avaliable;

    private String userName;

    private Integer price;

    private String SKU;

    public PianoResponse(Long id, String model, String producer, Boolean avaliable, String userName, Integer price, String SKU) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.avaliable = avaliable;
        this.userName = userName;
        this.price = price;
        this.SKU = SKU;
    }

    public PianoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
