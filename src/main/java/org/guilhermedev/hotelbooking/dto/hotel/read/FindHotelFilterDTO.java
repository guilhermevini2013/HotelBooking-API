package org.guilhermedev.hotelbooking.dto.hotel.read;

import jakarta.validation.constraints.Size;

import java.util.Set;

public record FindHotelFilterDTO(
        String field,
        Double price,
        @Size(max = 3)
        Set<TypeFilterHotel> typesFilter
) {
}
