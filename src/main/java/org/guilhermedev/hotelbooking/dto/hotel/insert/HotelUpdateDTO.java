package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.models.hotel.SizeType;

public record HotelUpdateDTO(
        Long idEnterprise,
        String name,
        String description,
        SizeType sizeHotel) {
}
