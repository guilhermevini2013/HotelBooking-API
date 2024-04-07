package org.guilhermedev.hotelbooking.dto.contact.read;

import org.guilhermedev.hotelbooking.models.information.Contact;

public class ContactReadDTO {
    final private Long id;
    final private String numberPhone;
    final private String email;

    public ContactReadDTO(Contact contact) {
        this.id = contact.getId();
        this.numberPhone = contact.getNumberPhone();
        this.email = contact.getEmail();
    }
}