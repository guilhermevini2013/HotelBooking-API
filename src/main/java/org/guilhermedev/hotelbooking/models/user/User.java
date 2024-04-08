package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity
@DiscriminatorColumn(name = "Type")
@Table(name = "users")
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @Column(unique = true)
    protected String email;
    protected String password;
    @Column(unique = true)
    protected String identity;
    protected String phone;
    @ManyToMany
    protected Set<RoleType> roles;

    public User(Long id, String name, String email, String password, String identity, String phone, Set<RoleType> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identity = identity;
        this.phone = phone;
        this.roles = roles;
    }

    public User() {
    }

    public String getPhone() {
        return phone;
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

    @Override
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
