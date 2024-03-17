package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberPhone;
    private String email;

    protected Contact() {
    }

    public Contact(ContactDTO contact) {
        this.numberPhone = contact.numberPhone();
        this.email = contact.email();
    }

    public Long getId() {
        return id;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getEmail() {
        return email;
    }
}
