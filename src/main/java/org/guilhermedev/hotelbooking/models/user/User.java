package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String identity;
    @OneToMany
    private Set<RoleType> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String name, String email, String password, String identity, Set<RoleType> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identity = identity;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentity() {
        return identity;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }
}
