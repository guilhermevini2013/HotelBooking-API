package org.guilhermedev.hotelbooking.dto.user.insert;

import org.guilhermedev.hotelbooking.dto.contact.insert.ContactCreateDTO;
import org.guilhermedev.hotelbooking.models.user.TypeUser;

import java.util.Date;

/**
 * DTO for {@link org.guilhermedev.hotelbooking.models.user.Client}
 */
public record UserRegisterDTO(String name, String email, String password, String identity, Date dateOfBirth,
                              String gender, ContactCreateDTO contact, TypeUser typeUser) {

}