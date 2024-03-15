package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.guilhermedev.hotelbooking.models.information.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends User {
    private Date dateOfBirth;
    private String gender;
    @OneToOne(fetch = FetchType.LAZY)
    private Contact contact;
    @OneToMany
    private List<Booking> bookings = new ArrayList<>();

    public Client() {
    }
}
