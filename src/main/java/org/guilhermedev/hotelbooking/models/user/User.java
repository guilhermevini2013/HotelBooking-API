package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected String name;
    @Column(unique = true)
    protected String email;
    protected String password;
    @Column(unique = true)
    protected String identity;
    @OneToMany
    protected Set<RoleType> roles = new HashSet<>();

    public User() {
    }
}
