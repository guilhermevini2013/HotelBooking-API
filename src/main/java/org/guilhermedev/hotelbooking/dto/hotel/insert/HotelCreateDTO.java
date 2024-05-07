package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.dto.address.insert.AddressCreateDTO;
import org.guilhermedev.hotelbooking.dto.contact.insert.ContactCreateDTO;
import org.guilhermedev.hotelbooking.models.hotel.SizeType;

import java.util.List;

public record HotelCreateDTO(
        String name,
        String description,
        Double price,
        SizeType sizeHotel,
        ContactCreateDTO contact,
        AddressCreateDTO address,
        List<String> imageBase64) {
}
