package org.guilhermedev.hotelbooking.services.enterprise;

import org.guilhermedev.hotelbooking.dto.booking.read.BookingPendingDTO;
import org.guilhermedev.hotelbooking.models.user.Booking;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.repositories.BookingRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnterpriseService {
    private final BookingRepository bookingRepository;

    public EnterpriseService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional(readOnly = true)
    public Page<BookingPendingDTO> findAllBookingPending(PageRequest pageRequest){
        Enterprise enterprise = (Enterprise) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingRepository.findAllBookingPending(enterprise.getId(),pageRequest)
                .map(booking -> new BookingPendingDTO(booking));
    }

    @Transactional
    public void acceptBooking(Long idBooking) {
        Booking bookingFound = bookingRepository.findById(idBooking).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingFound.setAccept(true);
    }

    @Transactional
    public void recuseBooking(Long idBooking) {
        bookingRepository.deleteById(idBooking);
    }
}
