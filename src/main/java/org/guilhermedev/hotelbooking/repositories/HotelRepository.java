package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    @Query("select h from Hotel h where h.enterprise.id = :id")
    Optional<Hotel> findByEnterpriseId(Long id);

    @Query("SELECT h FROM Hotel h WHERE h.address.city LIKE %:city%")
    Page<Hotel> findAllLikeByCity(String city, Pageable pageRequest);
}