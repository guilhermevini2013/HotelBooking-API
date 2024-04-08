package org.guilhermedev.hotelbooking.dto.user.insert;

import jakarta.validation.constraints.*;
import org.guilhermedev.hotelbooking.models.user.TypeUser;

import java.util.Date;

/**
 * DTO for {@link org.guilhermedev.hotelbooking.models.user.Client}
 */
public record UserRegisterDTO(
        @NotBlank(message = "Nome não pode ser vazio.")
        @Size(min = 3, max = 50, message = "Nome deve conter no minimo 3 e no máximo 50 caracteres.")
        String name,
        @NotBlank(message = "Email não pode ser vazio.")
        @Email(message = "Email incorreto.")
        String email,
        @NotBlank(message = "Senha não pode ser vazio.")
        @Size(min = 6, message = "A senha tem que ter no mínimo 6 caracteres.")
        String password,
        @NotBlank(message = "Identidade não pode ser vazio")
        @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$", message = "CPF ou CNPJ inválido")
        String identity,
        @PastOrPresent(message = "Data de nascimento não pode ser no futuro.")
        Date dateOfBirth,
        @NotBlank(message = "Telefone não pode ser vazio")
        @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4}-\\d{5}$", message = "Número de telefone inválido. Use o formato +00 (00) 0000-00000.")
        String numberPhone,
        @NotNull(message = "Tipo de usuário não pode ser vazio.")
        TypeUser typeUser) {

}