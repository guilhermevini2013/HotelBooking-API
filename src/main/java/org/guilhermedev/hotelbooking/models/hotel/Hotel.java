package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Commentary;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;
import org.guilhermedev.hotelbooking.models.user.Enterprise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Hotel {
    @OneToMany
    private final List<Commentary> commentaries = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> imagesHotel = new HashSet<>();
    @OneToMany
    private Set<Room> rooms = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private SizeType sizeHotel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "hotel")
    private Address address;
    @Embedded
    private InformationHotel informationHotel;
    @OneToOne(fetch = FetchType.LAZY)
    private Enterprise enterprise;

    private Hotel(Enterprise enterprise, String name, String description,
                  SizeType sizeHotel, Contact contact, Address address, InformationHotel informationHotel,
                  Set<Image> imagesHotel, Set<Room> rooms) {
        this.imagesHotel = imagesHotel;
        this.rooms = rooms;
        this.enterprise = enterprise;
        this.name = name;
        this.description = description;
        this.sizeHotel = sizeHotel;
        this.contact = contact;
        this.address = new Address(address,this);
        this.informationHotel = informationHotel;
    }

    protected Hotel() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public SizeType getSizeHotel() {
        return sizeHotel;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public InformationHotel getInformationHotel() {
        return informationHotel;
    }

    public static class Builder {
        private Set<Image> imagesHotel = new HashSet<>();
        private Set<Room> rooms = new HashSet<>();
        private String name;
        private String description;
        private SizeType sizeHotel;
        private Contact contact;
        private Address address;
        private InformationHotel informationHotel;
        private Enterprise enterprise;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder sizeHotel(SizeType sizeHotel) {
            this.sizeHotel = sizeHotel;
            return this;
        }

        public Builder enterprise(Enterprise enterprise) {
            this.enterprise = enterprise;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder informationHotel(InformationHotel informationHotel) {
            this.informationHotel = informationHotel;
            return this;
        }

        public Builder imagesHotel(Set<Image> imagesHotel) {
            this.imagesHotel = imagesHotel;
            return this;
        }

        public Builder rooms(Set<Room> rooms) {
            this.rooms = rooms;
            return this;
        }

        public Hotel build() {
            return new Hotel(enterprise, name, description, sizeHotel, contact, address, informationHotel, imagesHotel, rooms);
        }
    }
}
