package org.guilhermedev.hotelbooking.dto.user.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.guilhermedev.hotelbooking.dto.contact.insert.ContactCreateDTO;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadDTO extends RepresentationModel<UserReadDTO> {
    private final Long id;
    private final String name;
    private final String email;
    private final ContactCreateDTO contact;

    public UserReadDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.contact = new ContactCreateDTO(user.getContact());
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

    public ContactCreateDTO getContact() {
        return contact;
    }
}
