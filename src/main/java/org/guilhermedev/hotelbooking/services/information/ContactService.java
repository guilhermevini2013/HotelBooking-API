package org.guilhermedev.hotelbooking.services.information;

import jakarta.transaction.Transactional;
import org.guilhermedev.hotelbooking.dto.contact.insert.ContactUpdateDTO;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.ContactRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public void updateContact(ContactUpdateDTO contactUpdateDTO) {
        Enterprise enterprise = (Enterprise) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Contact contactFound = contactRepository.findContactById(enterprise.getHotel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        contactFound.update(contactUpdateDTO);
    }
}
