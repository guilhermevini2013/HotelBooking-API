package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.InformationHotel;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private SizeType sizeHotel;
    @OneToOne(fetch = FetchType.LAZY)
    private Contact contact;
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
    @Embedded
    private InformationHotel informationHotel;
//    @Lob
//    private Set<byte[]> images = new HashSet<>();
    @OneToMany
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {
    }
}
