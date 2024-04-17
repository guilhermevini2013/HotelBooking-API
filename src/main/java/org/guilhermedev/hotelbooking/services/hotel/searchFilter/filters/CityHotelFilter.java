package org.guilhermedev.hotelbooking.services.hotel.searchFilter.filters;

import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.TypeFilterHotel;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.services.hotel.searchFilter.IHotelFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CityHotelFilter implements IHotelFilter {
    private final HotelRepository hotelRepository;

    public CityHotelFilter(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void filter(FindHotelFilterDTO findHotelFilterDTO, Set<HotelResharedDTO> resultSet, PageRequest pageable) {
        if (findHotelFilterDTO.typesFilter().contains(TypeFilterHotel.CITY)) {
            hotelRepository.findAllLikeByCity(findHotelFilterDTO.field(), pageable)
                    .forEach(result -> resultSet.add(new HotelResharedDTO(result)));
        }
    }
}
