package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.guilhermedev.hotelbooking.services.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/booking")
@RestController
public class BookingController {
    private final ClientService clientService;

    public BookingController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BookingCreateDTO bookingCreateDTO) {
        clientService.bookingHotel(bookingCreateDTO);
        return ResponseEntity.noContent().build();
    }
}
