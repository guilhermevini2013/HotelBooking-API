package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.dto.contact.insert.ContactCreateDTO;
import org.guilhermedev.hotelbooking.models.hotel.SizeType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public record HotelCreateDTO(
        String name,
        String description,
        SizeType sizeHotel,
        ContactCreateDTO contact,
        AddressCreateDTO address) {
}
