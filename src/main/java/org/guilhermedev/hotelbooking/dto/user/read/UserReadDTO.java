package org.guilhermedev.hotelbooking.dto.user.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadDTO extends RepresentationModel<UserReadDTO> {
    private final Long id;
    private final String name;
    private final String email;
    private final String phone;

    public UserReadDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
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

    public String getContact() {
        return phone;
    }
}
