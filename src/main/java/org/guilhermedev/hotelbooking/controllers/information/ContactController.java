package org.guilhermedev.hotelbooking.controllers.information;

import org.guilhermedev.hotelbooking.dto.contact.insert.ContactUpdateDTO;
import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.services.information.AddressService;
import org.guilhermedev.hotelbooking.services.information.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotel/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PutMapping
    public ResponseEntity<Void> updateAddress(@RequestBody ContactUpdateDTO contactUpdateDTO) {
        contactService.updateContact(contactUpdateDTO);
        return ResponseEntity.ok().build();
    }
}
