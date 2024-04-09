package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}