package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.guilhermedev.hotelbooking.dto.booking.insert.BookingCreateDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@DiscriminatorValue("Client")
public class Client extends User {
    private Date dateOfBirth;
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Booking> bookings = new ArrayList<>();

    private Client(Long id, String name, String email, String password, String identity, String phone, Set<RoleType> roles, Date dateOfBirth) {
        super(id, name, email, password, identity, phone, roles);
        this.dateOfBirth = dateOfBirth;
    }


    protected Client() {

    }
    public void insertBookings(Booking booking){
        bookings.add(booking);
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


    public List<Booking> getBookings() {
        return bookings;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String identity;
        private Set<RoleType> roles;
        private Date dateOfBirth;
        private String phone;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Client build() {
            return new Client(id, name, email, password, identity, phone, roles, dateOfBirth);
        }
    }
}
