package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
