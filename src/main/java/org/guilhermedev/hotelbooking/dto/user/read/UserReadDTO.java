package org.guilhermedev.hotelbooking.dto.user.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadDTO extends RepresentationModel<UserReadDTO> {
    private final Long id;
    private final String name;
    private final String email;
    private final ContactDTO contact;

    public UserReadDTO(User enterprise) {
        this.id = enterprise.getId();
        this.name = enterprise.getName();
        this.email = enterprise.getEmail();
        this.contact = new ContactDTO(enterprise.getContact());
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

    public ContactDTO getContact() {
        return contact;
    }
}
