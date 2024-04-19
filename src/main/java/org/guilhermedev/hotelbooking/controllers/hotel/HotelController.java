package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelCreateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.HotelUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.FindHotelFilterDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelReadDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelResharedDTO;
import org.guilhermedev.hotelbooking.dto.hotel.read.HotelSelectDTO;
import org.guilhermedev.hotelbooking.services.hotel.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<HotelSelectDTO> findById(@PathVariable Long id) {
        HotelSelectDTO hotelFind = hotelService.findById(id);
        return ResponseEntity.ok(hotelFind);
    }

    @GetMapping(value = "/byFilter")
    public ResponseEntity<Page<HotelResharedDTO>> findAllLikeByCity(@RequestBody FindHotelFilterDTO findHotelFilterDTO,
                                                                    @RequestParam(name = "linesPerPage", defaultValue = "15") Integer linesPerPage,
                                                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                    @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
        Page<HotelResharedDTO> allByCity = hotelService.findAllByFilter(PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy), findHotelFilterDTO);
        return ResponseEntity.ok(allByCity);
    }
}
