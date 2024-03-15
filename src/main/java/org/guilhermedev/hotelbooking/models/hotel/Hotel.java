package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;

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
    @Lob
    private Set<byte[]> images = new HashSet<>();
    @OneToMany
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {
    }
}
