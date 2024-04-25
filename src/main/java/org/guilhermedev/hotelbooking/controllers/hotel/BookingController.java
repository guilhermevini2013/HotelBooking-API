package org.guilhermedev.hotelbooking.controllers.hotel;

import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.guilhermedev.hotelbooking.dto.booking.read.BookingPendingDTO;
import org.guilhermedev.hotelbooking.services.client.ClientService;
import org.guilhermedev.hotelbooking.services.enterprise.EnterpriseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping(value = "/pending")
    public ResponseEntity<Page<BookingPendingDTO>> findAllBookingPending(@RequestParam(name = "linesPerPage", defaultValue = "15") Integer linesPerPage,
                                                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                         @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                         @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {

        return ResponseEntity.ok(enterpriseService.findAllBookingPending(PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy)));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BookingCreateDTO bookingCreateDTO) {
        clientService.bookingHotel(bookingCreateDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<Void> acceptBooking(@PathVariable Long id) {
        enterpriseService.acceptBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/recuse/{id}")
    public ResponseEntity<Void> recuseBooking(@PathVariable Long id) {
        enterpriseService.recuseBooking(id);
        return ResponseEntity.noContent().build();
    }
}
