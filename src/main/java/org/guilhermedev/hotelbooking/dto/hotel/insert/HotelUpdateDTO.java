package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.models.hotel.SizeType;

import java.util.List;

public record HotelUpdateDTO(
        String name,
        String description,
        SizeType sizeHotel,
        List<String> imageBase64) {
}
