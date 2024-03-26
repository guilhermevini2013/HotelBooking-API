package org.guilhermedev.hotelbooking.dto.contact.insert;

import org.guilhermedev.hotelbooking.models.information.Contact;

public record ContactCreateDTO(String numberPhone, String email) {
    public ContactCreateDTO(Contact contact) {
        this(contact.getNumberPhone(), contact.getEmail());
    }
}
