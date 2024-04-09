package org.guilhermedev.hotelbooking.dto.contact.insert;

public record ContactUpdateDTO(
        Long idHotel,
        String phoneNumber,
        String email) {
}
