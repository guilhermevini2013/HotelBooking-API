package org.guilhermedev.hotelbooking.services.hotel;

import jakarta.persistence.EntityNotFoundException;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelSelectDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.hotel.InformationHotel;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.information.Image;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.repositories.ImageRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.guilhermedev.hotelbooking.services.hotel.searchFilter.HotelFilterChain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final HotelFilterChain hotelFilterChain;

    public HotelService(HotelRepository hotelRepository, ImageService imageService, ImageRepository imageRepository, HotelFilterChain hotelFilterChain) {
        this.hotelRepository = hotelRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.hotelFilterChain = hotelFilterChain;
    }

    @Transactional(readOnly = true)
    public Page<HotelResharedDTO> findAllByFilter(PageRequest pageRequest, FindHotelFilterDTO findHotelFilterDTO) {
        return hotelFilterChain.filter(findHotelFilterDTO, pageRequest);
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
                .price(hotelCreateDTO.price())
                .imagesHotel(imageService.transformBase64(images))
                .build();
        hotel = hotelRepository.save(hotel);
        return new HotelReadDTO(hotel);
    }

    @Transactional(readOnly = true)
    public HotelSelectDTO findById(Long id){
        Hotel hotelFound = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return new HotelSelectDTO(hotelFound);
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
