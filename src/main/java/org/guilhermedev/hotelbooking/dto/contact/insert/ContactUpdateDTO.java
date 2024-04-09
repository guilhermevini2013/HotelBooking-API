package org.guilhermedev.hotelbooking.dto.contact.insert;

public record ContactUpdateDTO(
        Long idEnterprise,
        String phoneNumber,
        String email) {
}
