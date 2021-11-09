package com.lnko.model.entity;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private User user;
    private Tariff tariff;
    private LocalDateTime dateTime;

    public Order() {};

    public Order(Object o, User user, Tariff tariff, LocalDateTime now) {
        this.user = user;
        this.tariff = tariff;
        this.dateTime = now;
    }

    public Order(Long id, User user, Tariff tariff, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.tariff = tariff;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}