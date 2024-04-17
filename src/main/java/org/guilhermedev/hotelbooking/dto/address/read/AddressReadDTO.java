package org.guilhermedev.hotelbooking.dto.address.read;

import org.guilhermedev.hotelbooking.models.hotel.Coordinate;
import org.guilhermedev.hotelbooking.models.information.Address;

public class AddressReadDTO {
    private final Long id;
    private final String street;
    private final Integer streetNumber;
    private final String city;
    private final String district;
    private final Coordinate coordinate;

    public AddressReadDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.streetNumber = address.getStreetNumber();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.coordinate = address.getCoordinate();
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
