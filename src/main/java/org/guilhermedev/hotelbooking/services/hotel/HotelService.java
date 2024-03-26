package org.guilhermedev.hotelbooking.services.hotel;

import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public HotelService(HotelRepository hotelRepository, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public HotelReadDTO insert(HotelCreateDTO hotelCreateDTO) {
        Enterprise enterprise = getReferenceById(hotelCreateDTO.idEnterprise());
        Hotel hotel = new Hotel.Builder()
                .name(hotelCreateDTO.name())
                .address(new Address(hotelCreateDTO.address()))
                .contact(new Contact(hotelCreateDTO.contact()))
                .sizeHotel(hotelCreateDTO.sizeHotel())
                .description(hotelCreateDTO.description())
                .informationHotel(new InformationHotel())
                .enterprise(enterprise)
                .imagesHotel(getImages(hotelCreateDTO.images()))
                .build();
        hotel = hotelRepository.save(hotel);
        return new HotelReadDTO(hotel);
    }

    private Set<Image> getImages(Set<MultipartFile> files) {
        return files.stream().map(file -> {
            try {
                return new Image(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
    }

    private Enterprise getReferenceById(Long id) {
        return (Enterprise) userRepository.getReferenceById(id);
    }
}
