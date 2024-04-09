package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressCreateDTO;
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
    @OneToOne
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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Address() {

    }
}
