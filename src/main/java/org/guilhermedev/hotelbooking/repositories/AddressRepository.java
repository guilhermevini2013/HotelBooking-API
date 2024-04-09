package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.information.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.hotel.id= :id")
    Optional<Address> findByHotelId(Long id);
}
