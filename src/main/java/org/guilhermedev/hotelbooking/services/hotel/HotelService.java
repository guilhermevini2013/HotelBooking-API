package org.guilhermedev.hotelbooking.services.hotel;

import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelReadDTO insert(HotelCreateDTO hotelCreateDTO){

        return null;
    }
}
