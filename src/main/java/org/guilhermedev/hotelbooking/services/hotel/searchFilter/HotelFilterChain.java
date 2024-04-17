package org.guilhermedev.hotelbooking.services.hotel.searchFilter;

import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HotelFilterChain {
    private final Set<IHotelFilter> filterChain;

    public HotelFilterChain(Set<IHotelFilter> filterChain) {
        this.filterChain = filterChain;
    }

    public Page<HotelResharedDTO> filter(FindHotelFilterDTO findHotelFilterDTO, PageRequest pageRequest) {
        Set<HotelResharedDTO> resultSet = new HashSet<>();
        filterChain.forEach(filter -> filter.filter(findHotelFilterDTO, resultSet, pageRequest));
        return new PageImpl<>(resultSet.stream().toList(), pageRequest, resultSet.size());
    }
}
