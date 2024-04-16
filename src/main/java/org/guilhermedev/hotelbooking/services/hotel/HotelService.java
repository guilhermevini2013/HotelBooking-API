package org.guilhermedev.hotelbooking.services.hotel;

import jakarta.persistence.EntityNotFoundException;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.repositories.ImageRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public HotelService(HotelRepository hotelRepository, UserRepository userRepository, ImageService imageService, ImageRepository imageRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @Transactional(readOnly = true)
    public Page<HotelReadDTO> findAllByCity(PageRequest pageRequest, String city) {
        return hotelRepository.findAllLikeByCity(city.toLowerCase(), pageRequest).map(hotel -> new HotelReadDTO(hotel));
    }

    @Transactional
    public HotelReadDTO insert(HotelCreateDTO hotelCreateDTO, List<MultipartFile> images) {
        Enterprise principal = (Enterprise) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotel = new Hotel.Builder()
                .name(hotelCreateDTO.name())
                .address(new Address(hotelCreateDTO.address()))
                .contact(new Contact(hotelCreateDTO.contact()))
                .sizeHotel(hotelCreateDTO.sizeHotel())
                .description(hotelCreateDTO.description())
                .informationHotel(new InformationHotel())
                .enterprise(principal)
                .imagesHotel(imageService.transformBase64(images))
                .build();
        hotel = hotelRepository.save(hotel);
        return new HotelReadDTO(hotel);
    }

    @Transactional
    public void update(HotelUpdateDTO hotelUpdateDTO, List<MultipartFile> images) {
        Enterprise enterprise = (Enterprise) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotel = hotelRepository.findByEnterpriseId(enterprise.getId()).orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        copyEntity(hotelUpdateDTO, images, hotel);
    }

    private void copyEntity(HotelUpdateDTO hotelUpdateDTO, List<MultipartFile> images, Hotel hotel) {
        for (Image image : hotel.getImagesHotel()) {
            imageRepository.delete(image);
        }
        hotel.updateHotel(hotelUpdateDTO, imageService.transformBase64(images));
    }
}
