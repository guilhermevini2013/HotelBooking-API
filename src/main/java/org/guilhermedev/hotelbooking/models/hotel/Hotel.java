package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Commentary;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Hotel {
    @OneToMany
    private final Set<Image> imagesHotel = new HashSet<>();
    @OneToMany
    private final Set<Room> rooms = new HashSet<>();
    @OneToMany
    private final List<Commentary> commentaries = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private SizeType sizeHotel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    @Embedded
    private InformationHotel informationHotel;

    private Hotel(String name, String description, SizeType sizeHotel, Contact contact, Address address, InformationHotel informationHotel) {
        this.name = name;
        this.description = description;
        this.sizeHotel = sizeHotel;
        this.contact = contact;
        this.address = address;
        this.informationHotel = informationHotel;
    }

    protected Hotel() {
    }

    public static class Builder {
        private final Set<Image> imagesHotel = new HashSet<>();
        private final Set<Room> rooms = new HashSet<>();
        private final List<Commentary> commentaries = new ArrayList<>();
        private String name;
        private String description;
        private SizeType sizeHotel;
        private Contact contact;
        private Address address;
        private InformationHotel informationHotel;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSizeHotel(SizeType sizeHotel) {
            this.sizeHotel = sizeHotel;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setInformationHotel(InformationHotel informationHotel) {
            this.informationHotel = informationHotel;
            return this;
        }

        public Hotel build() {
            return new Hotel(name, description, sizeHotel, contact, address, informationHotel);
        }
    }
}
