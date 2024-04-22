package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.user.Client;

import java.time.LocalDate;

@Entity
public class BookingIncomplete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate initialDate;
    private LocalDate finalDate;
    @ManyToOne
    private Hotel hotel;
    @OneToOne
    private Client client;

    public BookingIncomplete() {
    }

    public BookingIncomplete(LocalDate initialDate, LocalDate finalDate, Hotel hotel, Client client) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.hotel = hotel;
        this.client = client;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public Client getClient() {
        return client;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
