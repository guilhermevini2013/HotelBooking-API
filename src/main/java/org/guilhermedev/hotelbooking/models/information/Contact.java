package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.contact.insert.ContactCreateDTO;
import org.guilhermedev.hotelbooking.dto.contact.insert.ContactUpdateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberPhone;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    protected Contact() {
    }

    public Contact(ContactCreateDTO contact) {
        this.numberPhone = contact.numberPhone();
        this.email = contact.email();
    }

    public Contact(Contact contact, Hotel hotel) {
        this.numberPhone = contact.numberPhone;
        this.email = contact.email;
        this.hotel = hotel;
    }
    public void update(ContactUpdateDTO contact) {
        this.numberPhone = contact.phoneNumber();
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
