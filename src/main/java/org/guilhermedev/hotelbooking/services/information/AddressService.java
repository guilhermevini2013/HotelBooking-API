package org.guilhermedev.hotelbooking.services.information;

import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.models.information.Address;
import org.guilhermedev.hotelbooking.repositories.AddressRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
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
        Address addressFound = addressRepository.findByHotelId(addressDTO.idHotel()).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        addressFound.update(addressDTO);
    }
}
