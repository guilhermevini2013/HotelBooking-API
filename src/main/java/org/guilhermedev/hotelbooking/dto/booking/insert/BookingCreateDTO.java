package org.guilhermedev.hotelbooking.dto.booking.insert;

import java.time.LocalDate;

public record BookingCreateDTO(
        Long idHotel,
        LocalDate initialDate,
        LocalDate finalDate
) {
}
