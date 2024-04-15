package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("select h from Hotel h where h.enterprise.id = :id")
    Optional<Hotel> findByEnterpriseId(Long id);
}