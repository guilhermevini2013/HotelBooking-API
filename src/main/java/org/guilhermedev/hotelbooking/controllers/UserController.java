package org.guilhermedev.hotelbooking.controllers;

import org.guilhermedev.hotelbooking.dto.user.ClientReadDTO;
import org.guilhermedev.hotelbooking.dto.user.ClientRegisterDTO;
import org.guilhermedev.hotelbooking.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/client")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ClientReadDTO> register(@RequestBody ClientRegisterDTO userRegisterDTO, UriComponentsBuilder componentsBuilder) {
        ClientReadDTO userRegister = userService.register(userRegisterDTO);
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(userRegister.getId()).toUri();
        return ResponseEntity.created(uri).body(userRegister);
    }
}
