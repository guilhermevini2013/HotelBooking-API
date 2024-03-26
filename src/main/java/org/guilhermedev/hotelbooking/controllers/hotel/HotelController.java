package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.services.hotel.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "hotel")
public class HotelController {
    final private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelReadDTO> create(@RequestBody HotelCreateDTO hotelCreateDTO, UriComponentsBuilder componentsBuilder) {
        HotelReadDTO hotelInserted = hotelService.insert(hotelCreateDTO);
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(hotelInserted.getId()).toUri();
        return ResponseEntity.created(uri).body(hotelInserted);
    }
}
