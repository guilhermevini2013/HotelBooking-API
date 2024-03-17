package org.guilhermedev.hotelbooking.repositories;

import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleType, Long> {
    RoleType findByAuthority(String authority);
}
