package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
