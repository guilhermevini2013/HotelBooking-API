package org.guilhermedev.hotelbooking.dto.hotel.read;

import org.guilhermedev.hotelbooking.dto.contact.read.ContactReadDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.hotel.SizeType;
import org.guilhermedev.hotelbooking.models.information.Address;

public class HotelReadDTO {
    final private Long id;
    final private String name;
    final private Integer totalBookings;
    final private Double totalEvaluations;
    final private String description;
    final private SizeType sizeHotel;
    final private ContactReadDTO contact;
    final private Address address;
    final private InformationHotel informationHotel;


    public HotelReadDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.totalBookings = hotel.getInformationHotel().getTotalBookings();
        this.totalEvaluations = hotel.getInformationHotel().getTotalEvaluations();
        this.description = hotel.getDescription();
        this.sizeHotel = hotel.getSizeHotel();
        this.contact = new ContactReadDTO(hotel.getContact());
        this.address = null;
        this.informationHotel = hotel.getInformationHotel();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public Double getTotalEvaluations() {
        return totalEvaluations;
    }

    public String getDescription() {
        return description;
    }

    public SizeType getSizeHotel() {
        return sizeHotel;
    }

    public ContactReadDTO getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public InformationHotel getInformationHotel() {
        return informationHotel;
    }
}
