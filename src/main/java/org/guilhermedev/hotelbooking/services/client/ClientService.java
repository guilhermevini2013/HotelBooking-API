package org.guilhermedev.hotelbooking.services.client;

import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.user.Booking;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    public ClientService(HotelRepository hotelRepository, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void bookingHotel(BookingCreateDTO bookingCreateDTO) {
        Client clientAuthenticate = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotelFind = hotelRepository.findById(bookingCreateDTO.idHotel()).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelFind.insertNotificationBooking(bookingCreateDTO, clientAuthenticate);
    }
}
