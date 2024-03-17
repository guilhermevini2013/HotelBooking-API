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
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    public Booking() {
    }
}
