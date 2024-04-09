package org.guilhermedev.hotelbooking.services.information;

import org.guilhermedev.hotelbooking.dto.hotel.insert.AddressUpdateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Coordinate;
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
        copyEntity(addressDTO, addressFound);
    }

    private void copyEntity(AddressUpdateDTO addressDTO, Address addressFound) {
        addressFound.setCity(addressDTO.city());
        addressFound.setCoordinate(new Coordinate(addressDTO.positionX(), addressDTO.positionY()));
        addressFound.setStreetNumber(addressDTO.streetNumber());
        addressFound.setStreet(addressDTO.street());
        addressFound.setDistrict(addressDTO.district());
    }
}
