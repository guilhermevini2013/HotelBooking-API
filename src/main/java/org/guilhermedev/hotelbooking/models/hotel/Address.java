package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer streetNumber;
    private String city;
    private String district;
    @Embedded
    private Coordinate coordinate;

    public Address() {
    }
}
