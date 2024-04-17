package org.guilhermedev.hotelbooking.dto.hotel.read;

import org.guilhermedev.hotelbooking.dto.address.read.AddressReadDTO;
import org.guilhermedev.hotelbooking.dto.contact.read.ContactReadDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.hotel.SizeType;

public class HotelReadDTO {
    final private Long id;
    final private String name;
    final private String description;
    final private SizeType sizeHotel;
    final private Double price;
    final private ContactReadDTO contact;
    final private AddressReadDTO address;
    final private InformationHotel informationHotel;


    public HotelReadDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.sizeHotel = hotel.getSizeHotel();
        this.contact = new ContactReadDTO(hotel.getContact());
        this.address = new AddressReadDTO(hotel.getAddress());
        this.informationHotel = hotel.getInformationHotel();
        this.price = hotel.getPrice();
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

    public Double getPrice() {
        return price;
    }

    public SizeType getSizeHotel() {
        return sizeHotel;
    }

    public ContactReadDTO getContact() {
        return contact;
    }

    public AddressReadDTO getAddress() {
        return address;
    }

    public InformationHotel getInformationHotel() {
        return informationHotel;
    }
}
