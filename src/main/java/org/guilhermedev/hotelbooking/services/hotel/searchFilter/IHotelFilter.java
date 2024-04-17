package org.guilhermedev.hotelbooking.services.hotel.searchFilter;

import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

public interface IHotelFilter {
    void filter(FindHotelFilterDTO findHotelFilterDTO, Set<HotelResharedDTO> resultSet, PageRequest pageable);
}
