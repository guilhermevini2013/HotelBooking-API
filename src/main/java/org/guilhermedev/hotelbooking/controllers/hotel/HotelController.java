package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.services.hotel.HotelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    final private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HotelReadDTO> create(@RequestPart HotelCreateDTO hotelCreateDTO, @RequestPart List<MultipartFile> images, UriComponentsBuilder componentsBuilder) {
        HotelReadDTO hotelInserted = hotelService.insert(hotelCreateDTO, images);
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(hotelInserted.getId()).toUri();
        return ResponseEntity.created(uri).body(hotelInserted);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(@RequestPart HotelUpdateDTO hotelUpdateDTO, @RequestPart List<MultipartFile> images) {
        hotelService.update(hotelUpdateDTO, images);
        return ResponseEntity.ok().build();
    }
}
