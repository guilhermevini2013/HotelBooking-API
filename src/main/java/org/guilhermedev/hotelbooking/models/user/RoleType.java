package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class RoleType implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public RoleType(String authority) {
        this.authority = authority;
    }

    protected RoleType() {

    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
