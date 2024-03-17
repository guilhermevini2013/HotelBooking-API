package org.guilhermedev.hotelbooking.services;

import org.guilhermedev.hotelbooking.controllers.UserController;
import org.guilhermedev.hotelbooking.dto.user.ClientReadDTO;
import org.guilhermedev.hotelbooking.dto.user.ClientRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.repositories.ClientRepository;
import org.guilhermedev.hotelbooking.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(ClientRepository clientRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ClientReadDTO register(ClientRegisterDTO clientRegisterDTO) {
        RoleType role = roleRepository.findByAuthority("ROLE_CLIENT");
        Client client = new Client.Builder()
                .email(clientRegisterDTO.email())
                .contact(clientRegisterDTO.contact())
                .password(passwordEncoder.encode(clientRegisterDTO.password()))
                .identity(clientRegisterDTO.identity())
                .name(clientRegisterDTO.name())
                .dateOfBirth(clientRegisterDTO.dateOfBirth())
                .gender(clientRegisterDTO.gender())
                .build();
        client = clientRepository.save(client);
        return new ClientReadDTO(client)
                .add(linkTo(methodOn(UserController.class).register(clientRegisterDTO, null))
                        .withSelfRel());
    }
}
