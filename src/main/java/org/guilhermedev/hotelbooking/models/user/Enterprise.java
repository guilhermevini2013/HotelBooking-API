package org.guilhermedev.hotelbooking.models.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.information.Contact;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue("Enterprise")
public class Enterprise extends User {
    @OneToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    private Enterprise(Long id, String name, String email, String password, String identity, Contact contact, Set<RoleType> roles) {
        super(id, name, email, password, identity, contact, roles);
    }

    protected Enterprise() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return super.identity;
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

    public static class Builder {
        protected Set<RoleType> roles;
        private Long id;
        private String name;
        private String email;
        private String password;
        private String identity;
        private Contact contact;

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

        public Builder contact(ContactDTO contactDTO) {
            this.contact = new Contact(contactDTO);
            return this;
        }

        public Builder roles(Set<RoleType> roles) {
            this.roles = roles;
            return this;
        }

        public Enterprise build() {
            return new Enterprise(id,name, email, password, identity, contact, roles);
        }
    }

}
