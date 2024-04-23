package org.guilhermedev.hotelbooking.services.enterprise;

import org.guilhermedev.hotelbooking.models.user.Booking;
import org.guilhermedev.hotelbooking.repositories.BookingRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnterpriseService {
    private final BookingRepository bookingRepository;

    public EnterpriseService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void acceptBooking(Long idBooking) {
        Booking bookingFound = bookingRepository.findById(idBooking).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingFound.setAccept(true);
    }
}
