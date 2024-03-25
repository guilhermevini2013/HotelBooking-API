package org.guilhermedev.hotelbooking.dto.hotel.insert;

public record AddressCreateDTO(
        String street,
        Integer streetNumber,
        String city,
        String district,
        Double positionX,
        Double positionY) {
}
