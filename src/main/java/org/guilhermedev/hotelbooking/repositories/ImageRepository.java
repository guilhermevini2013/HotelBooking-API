package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.information.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
