package com.RestaurantReservationSystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Reservation {
    private long id;
    private long clientId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private String status;

    public Reservation(long id, long clientId, LocalDateTime reservationDate, int numberOfPeople, String status) {
        this.id = id;
        this.clientId = clientId;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
    }

    public Reservation(long id, long clientId, LocalDateTime reservationDate, int numberOfPeople) {
        this.id = id;
        this.clientId = clientId;
        this.reservationDate = reservationDate;
        this.numberOfPeople = numberOfPeople;
    }

    public Reservation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
