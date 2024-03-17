package org.guilhermedev.hotelbooking.dto.user;

import org.guilhermedev.hotelbooking.models.user.Client;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class ClientReadDTO extends RepresentationModel<ClientReadDTO> {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String identity;
    private final Date dateOfBirth;
    private final String gender;
    private final ContactDTO contact;

    public ClientReadDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.identity = client.getIdentity();
        this.dateOfBirth = client.getDateOfBirth();
        this.gender = client.getGender();
        this.contact = new ContactDTO(client.getContact());
    }

    public Long getId() {
        return id;
    }
}
