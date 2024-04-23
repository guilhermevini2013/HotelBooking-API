package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Commentary;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;
import org.guilhermedev.hotelbooking.models.user.Booking;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.Enterprise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Hotel {
    @OneToMany(cascade = CascadeType.PERSIST)
    private final List<Commentary> commentaries = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> imagesHotel = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private SizeType sizeHotel;
    private Double price;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "hotel")
    private Contact contact;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "hotel")
    private Address address;
    @Embedded
    private InformationHotel informationHotel;
    @OneToOne(fetch = FetchType.LAZY)
    private Enterprise enterprise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private final List<Booking> notificationBooking = new ArrayList<>();

    private Hotel(Long id, Enterprise enterprise, String name, String description,
                  SizeType sizeHotel, Contact contact, Address address, InformationHotel informationHotel,
                  Set<Image> imagesHotel, Double price) {
        this.id = id;
        this.imagesHotel = imagesHotel;
        this.enterprise = enterprise;
        this.price = price;
        this.name = name;
        this.description = description;
        this.sizeHotel = sizeHotel;
        this.contact = new Contact(contact, this);
        this.address = new Address(address, this);
        this.informationHotel = informationHotel;
    }

    protected Hotel() {
    }

    public void insertNotificationBooking(BookingCreateDTO bookingCreateDTO, Client client) {
        Booking booking = new Booking(bookingCreateDTO.initialDate(), bookingCreateDTO.finalDate(), bookingCreateDTO.price(), client, this);
        notificationBooking.add(booking);
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public List<Booking> getNotificationBooking() {
        return notificationBooking;
    }

    public void calculateTotalEvaluations(Double newEvaluation) {
        Integer commentariesSize = commentaries.size() + 1;
        Double mediaEvaluations = commentaries.stream().mapToDouble(evaluation -> evaluation.getEvaluation()).sum() + newEvaluation;
        Double totalEvaluations = mediaEvaluations / commentariesSize;
        this.informationHotel.setTotalEvaluations(totalEvaluations);
    }

    public void updateHotel(HotelUpdateDTO hotelUpdateDTO, Set<Image> images) {
        this.imagesHotel = images;
        this.sizeHotel = hotelUpdateDTO.sizeHotel();
        this.name = hotelUpdateDTO.name();
        this.description = hotelUpdateDTO.description();
    }

    public Double getPrice() {
        return price;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
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

    public Set<Image> getImagesHotel() {
        return imagesHotel;
    }

    public static class Builder {
        private Long id;
        private Set<Image> imagesHotel = new HashSet<>();
        private Double price;
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

        public Builder id(Long id) {
            this.id = id;
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

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Hotel build() {
            return new Hotel(id, enterprise, name, description, sizeHotel, contact, address, informationHotel, imagesHotel, price);
        }
    }
}
