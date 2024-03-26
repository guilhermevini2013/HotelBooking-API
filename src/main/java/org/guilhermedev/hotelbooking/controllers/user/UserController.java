package org.guilhermedev.hotelbooking.controllers.user;

import org.guilhermedev.hotelbooking.dto.user.insert.UserLoginDTO;
import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserLoadDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserReadDTO;
import org.guilhermedev.hotelbooking.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserReadDTO> register(@RequestBody UserRegisterDTO userRegisterDTO, UriComponentsBuilder componentsBuilder) {
        UserReadDTO userRegister = userService.register(userRegisterDTO);
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(userRegister.getId()).toUri();
        return ResponseEntity.created(uri).body(userRegister);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<UserLoadDTO> auth(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }
}
