package org.guilhermedev.hotelbooking.services.information;

import org.guilhermedev.hotelbooking.dto.address.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.AddressRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void updateAddress(AddressUpdateDTO addressDTO) {
        Enterprise enterprise = (Enterprise) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Address addressFound = addressRepository.findByHotelId(enterprise.getHotel().getId()).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        addressFound.update(addressDTO);
    }
}
