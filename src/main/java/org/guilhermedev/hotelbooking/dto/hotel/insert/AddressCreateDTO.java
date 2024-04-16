package org.guilhermedev.hotelbooking.dto.hotel.insert;

import jakarta.validation.constraints.NotBlank;

public record AddressCreateDTO(
        @NotBlank(message = "Rua não pode ser vazio.")
        String street,
        @NotBlank(message = "Numero da rua não pode ser vazio.")
        Integer streetNumber,
        @NotBlank(message = "Cidade não pode ser vazio.")
        String city,
        @NotBlank(message = "Bairro não pode ser vazio.")
        String district,
        @NotBlank(message = "PositionX não pode ser vazio.")
        Double positionX,
        @NotBlank(message = "PositionY não pode ser vazio.")
        Double positionY) {
}
