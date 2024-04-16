package org.guilhermedev.hotelbooking.dto.contact.insert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.guilhermedev.hotelbooking.models.information.Contact;

public record ContactCreateDTO(
        @NotBlank(message = "Telefone não pode ser vazio")
        @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4}-\\d{5}$", message = "Número de telefone inválido. Use o formato +00 (00) 0000-00000.")
        String numberPhone,
        @NotBlank(message = "Email não pode ser vazio.")
        @Email(message = "Email incorreto.")
        String email) {
    public ContactCreateDTO(Contact contact) {
        this(contact.getNumberPhone(), contact.getEmail());
    }
}
