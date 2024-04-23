package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :credential OR u.identity = :credential")
    Optional<User> findByCredential(@Param("credential") String credential);
    @Query("select e from Enterprise e WHERE e.id= :id")
    Optional<Enterprise> findEnterpriseById(Long id);
    @Query("select c from Client c WHERE c.id= :id")
    Optional<Client> findClientById(Long id);
}
