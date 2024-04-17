package org.guilhermedev.hotelbooking.dto.address.insert;

public record AddressUpdateDTO(
        String street,
        Integer streetNumber,
        String city,
        String district,
        Double positionX,
        Double positionY) {
}
