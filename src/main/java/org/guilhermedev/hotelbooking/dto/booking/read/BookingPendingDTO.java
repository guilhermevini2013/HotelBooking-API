package org.guilhermedev.hotelbooking.dto.booking.read;

import org.guilhermedev.hotelbooking.models.user.Booking;

import java.time.LocalDate;

public record BookingPendingDTO(
        LocalDate initialDate,
        LocalDate finalDate,
        Integer totalPerson,
        Double price
) {
    public BookingPendingDTO(Booking booking) {
        this(booking.getInitialDate(), booking.getFinalDate(), booking.getTotalPerson(), booking.getPrice());
    }
}
