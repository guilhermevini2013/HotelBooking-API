package org.guilhermedev.hotelbooking.dto.user.insert;

import org.guilhermedev.hotelbooking.models.information.Contact;

public record ContactDTO(Long id, String numberPhone, String email) {
    public ContactDTO(Contact contact) {
        this(contact.getId(), contact.getNumberPhone(), contact.getEmail());
    }
}
