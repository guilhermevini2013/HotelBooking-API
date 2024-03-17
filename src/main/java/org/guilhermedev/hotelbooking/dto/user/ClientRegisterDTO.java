package org.guilhermedev.hotelbooking.dto.user;

import java.util.Date;

/**
 * DTO for {@link org.guilhermedev.hotelbooking.models.user.Client}
 */
public record ClientRegisterDTO(String name, String email, String password, String identity, Date dateOfBirth,
                                String gender, ContactDTO contact) {

}