package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@DiscriminatorValue("Client")
public class Client extends User {
    private Date dateOfBirth;
    private String gender;
    @OneToMany(mappedBy = "client")
    private List<Booking> bookings;

    private Client(String name, String email, String password, String identity, Set<RoleType> roles, Date dateOfBirth, String gender, Contact contact) {
        super(name, email, password, identity, contact, roles);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bookings = new ArrayList<>();
    }

    protected Client() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.roles;
    }

    @Override
    public String getPassword() {
        return super.password;
    }

    @Override
    public String getUsername() {
        return super.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private String identity;
        private Set<RoleType> roles;
        private Date dateOfBirth;
        private String gender;
        private Contact contact;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder identity(String identity) {
            this.identity = identity;
            return this;
        }

        public Builder roles(Set<RoleType> roles) {
            this.roles = roles;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder contact(ContactDTO contactDTO) {
            this.contact = new Contact(contactDTO);
            return this;
        }

        public Client build() {
            return new Client(name, email, password, identity, roles, dateOfBirth, gender, contact);
        }
    }
}
