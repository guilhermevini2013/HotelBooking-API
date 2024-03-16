package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.information.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany
    private Set<Image> imagesHotel = new HashSet<>();
    @OneToMany
    private Set<Room> rooms = new HashSet<>();
    @OneToMany
    private List<Commentary> commentaries = new ArrayList<>();

    public Hotel() {
    }
}
