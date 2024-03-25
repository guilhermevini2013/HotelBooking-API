package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.models.hotel.SizeType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record HotelCreateDTO(
        Long idEnterprise,
        String name,
        String description,
        SizeType sizeHotel,
        ContactCreateDTO contact,
        AddressCreateDTO address,
        Set<MultipartFile> Images,
        Set<RoomCreateDTO> rooms) {
}
