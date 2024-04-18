package org.guilhermedev.hotelbooking.dto.hotel.read;

import org.guilhermedev.hotelbooking.dto.address.read.AddressReadDTO;
import org.guilhermedev.hotelbooking.dto.commentary.read.CommentaryReadDTO;
import org.guilhermedev.hotelbooking.dto.contact.read.ContactReadDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.hotel.SizeType;

import java.util.List;

public record HotelSelectDTO(
        Long id,
        String name,
        String description,
        SizeType sizeHotel,
        Double price,
        ContactReadDTO contact,
        AddressReadDTO address,
        InformationHotel informationHotel,
        List<CommentaryReadDTO> commentaries
) {
    public HotelSelectDTO(Hotel hotel) {
        this(hotel.getId(), hotel.getName(), hotel.getDescription(), hotel.getSizeHotel(), hotel.getPrice(),
                new ContactReadDTO(hotel.getContact()), new AddressReadDTO(hotel.getAddress()), hotel.getInformationHotel(),
                hotel.getCommentaries().stream().map(CommentaryReadDTO::new).toList());
    }
}
