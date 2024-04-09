package org.guilhermedev.hotelbooking.dto.hotel.insert;

public record AddressUpdateDTO(
        Long idHotel,
        String street,
        Integer streetNumber,
        String city,
        String district,
        Double positionX,
        Double positionY) {
}
