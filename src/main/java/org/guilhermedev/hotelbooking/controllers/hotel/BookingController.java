package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.guilhermedev.hotelbooking.services.client.ClientService;
import org.guilhermedev.hotelbooking.services.enterprise.EnterpriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/booking")
@RestController
public class BookingController {
    private final ClientService clientService;
    private final EnterpriseService enterpriseService;

    public BookingController(ClientService clientService, EnterpriseService enterpriseService) {
        this.clientService = clientService;
        this.enterpriseService = enterpriseService;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BookingCreateDTO bookingCreateDTO) {
        clientService.bookingHotel(bookingCreateDTO);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/id/{id}")
    public ResponseEntity<Void> acceptBooking(@PathVariable Long id) {
        enterpriseService.acceptBooking(id);
        return ResponseEntity.noContent().build();
    }
}
