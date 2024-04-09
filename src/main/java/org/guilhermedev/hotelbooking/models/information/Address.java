package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Coordinate;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;

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
    @OneToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    public Address(AddressCreateDTO address) {
        this.street = address.street();
        this.streetNumber = address.streetNumber();
        this.city = address.city();
        this.district = address.district();
        this.coordinate = new Coordinate(address.positionX(), address.positionY());
    }

    public Address(Address address, Hotel hotel) {
        this.street = address.street;
        this.streetNumber = address.streetNumber;
        this.city = address.city;
        this.district = address.district;
        this.coordinate = address.coordinate;
        this.hotel = hotel;
    }

    public void update(AddressUpdateDTO addressUpdateDTO) {
        this.street = addressUpdateDTO.street();
        this.streetNumber = addressUpdateDTO.streetNumber();
        this.city = addressUpdateDTO.city();
        this.district = addressUpdateDTO.district();
        this.coordinate = new Coordinate(addressUpdateDTO.positionX(), addressUpdateDTO.positionY());
    }

    public Address() {

    }
}
