package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private Double price;
    private Integer totalPerson;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;
    private Boolean isAccept;

    public Booking() {
    }

    public Booking(LocalDate initialDate, LocalDate finalDate, Double price, Client client, Hotel hotel,Integer totalPerson) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.price = price;
        this.client = client;
        this.hotel = hotel;
        this.totalPerson = totalPerson;
        this.isAccept = false;
    }

    public Long getId() {
        return id;
    }

    public void setAccept(Boolean accept) {
        isAccept = accept;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public Integer getTotalPerson() {
        return totalPerson;
    }

    public Double getPrice() {
        return price;
    }

    public Client getClient() {
        return client;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Boolean getAccept() {
        return isAccept;
    }
}
