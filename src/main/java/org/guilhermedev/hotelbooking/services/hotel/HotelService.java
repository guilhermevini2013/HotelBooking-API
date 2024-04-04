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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public HotelService(HotelRepository hotelRepository, UserRepository userRepository, ImageService imageService) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Transactional
    public HotelReadDTO insert(HotelCreateDTO hotelCreateDTO, List<MultipartFile> images) {
        Enterprise enterprise = getReferenceById(hotelCreateDTO.idEnterprise());
        Hotel hotel = new Hotel.Builder()
                .name(hotelCreateDTO.name())
                .address(new Address(hotelCreateDTO.address()))
                .contact(new Contact(hotelCreateDTO.contact()))
                .sizeHotel(hotelCreateDTO.sizeHotel())
                .description(hotelCreateDTO.description())
                .informationHotel(new InformationHotel())
                .enterprise(enterprise)
                .imagesHotel(getImages(images))
                .build();
        hotel = hotelRepository.save(hotel);
        return new HotelReadDTO(hotel);
    }

    private Set<Image> getImages(List<MultipartFile> images) {
        return images.stream().map(image -> {
            try {
                return new Image(imageService.transformBase64(image));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
    }

    private Enterprise getReferenceById(Long id) {
        return userRepository.findEnterpriseById(id).get();
    }
}
