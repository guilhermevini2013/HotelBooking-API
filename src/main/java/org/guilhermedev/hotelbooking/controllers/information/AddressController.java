package org.guilhermedev.hotelbooking.controllers.information;

import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.services.information.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotel/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService, AddressService addressService1) {
        this.addressService = addressService1;
    }

    @PutMapping
    public ResponseEntity<Void> updateAddress(@RequestBody AddressUpdateDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.ok().build();
    }
}
