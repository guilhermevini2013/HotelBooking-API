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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected Contact contact;
    @OneToMany
    protected Set<RoleType> roles;

    protected User(String name, String email, String password, String identity, Contact contact, Set<RoleType> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.identity = identity;
        this.contact = contact;
        this.roles = roles;
    }

    public User() {
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

    public Contact getContact() {
        return contact;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }
}
