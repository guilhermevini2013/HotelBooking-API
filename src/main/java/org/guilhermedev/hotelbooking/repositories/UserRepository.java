package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
