package org.guilhermedev.hotelbooking.services.user;

import org.guilhermedev.hotelbooking.dto.user.insert.UserLoginDTO;
import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserLoadDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserReadDTO;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.guilhermedev.hotelbooking.services.user.strategy.create.ClientCreateStrategy;
import org.guilhermedev.hotelbooking.services.user.strategy.create.EnterpriseCreateStrategy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserStrategy userStrategy;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserService(UserStrategy userStrategy, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userStrategy = userStrategy;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserReadDTO register(UserRegisterDTO userRegisterDTO) {
        switch (userRegisterDTO.typeUser()) {
            case ENTERPRISE -> {
                return userStrategy.create(new EnterpriseCreateStrategy(), userRegisterDTO);
            }
            case CLIENT -> {
                return userStrategy.create(new ClientCreateStrategy(), userRegisterDTO);
            }
            default -> {
                throw new ResourceNotFoundException("Type user no exist");
            }
        }
    }

    @Transactional
    public UserLoadDTO login(UserLoginDTO userLoginDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.email(), userLoginDTO.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = jwtService.generateToken((User) authenticate.getPrincipal());
        return new UserLoadDTO(token);
    }
}
