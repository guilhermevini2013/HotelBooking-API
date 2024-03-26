package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressCreateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Coordinate;

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

    public Address(AddressCreateDTO address) {

    }
}
