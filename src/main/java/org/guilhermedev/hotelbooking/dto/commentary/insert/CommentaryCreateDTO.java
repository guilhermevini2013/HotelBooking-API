package org.guilhermedev.hotelbooking.dto.commentary.insert;

import jakarta.validation.constraints.*;

public record CommentaryCreateDTO(
        @Size(max = 255)
        String commentary,
        @PositiveOrZero
        @NotNull
        Double evaluation,
        @NotNull
        Long idHotel
) {
}
