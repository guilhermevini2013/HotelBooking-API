package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b where b.hotel.id= :idHotel and b.isAccept= FALSE")
    Page<Booking> findAllBookingPending(Long idHotel, Pageable pageRequest);
}
