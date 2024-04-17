package org.guilhermedev.hotelbooking.services.hotel.searchFilter.filters;

import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.TypeFilterHotel;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.services.hotel.searchFilter.FilterSpecification;
import org.guilhermedev.hotelbooking.services.hotel.searchFilter.IHotelFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NameHotelFilter implements IHotelFilter {
    private final HotelRepository hotelRepository;
    private final FilterSpecification<Hotel> hotelSpecification;

    public NameHotelFilter(HotelRepository hotelRepository, FilterSpecification<Hotel> hotelSpecification) {
        this.hotelRepository = hotelRepository;
        this.hotelSpecification = hotelSpecification;
    }

    @Override
    public void filter(FindHotelFilterDTO findHotelFilterDTO, Set<HotelResharedDTO> resultSet, PageRequest pageRequest) {
        if (findHotelFilterDTO.typesFilter().contains(TypeFilterHotel.NAME_HOTEL)) {
            hotelRepository.findAll(hotelSpecification.filterByString("name", findHotelFilterDTO.field()), pageRequest)
                    .forEach(result -> resultSet.add(new HotelResharedDTO(result)));
        }
    }
}
