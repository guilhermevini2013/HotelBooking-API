package org.guilhermedev.hotelbooking.services.user.strategy.login;

import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.springframework.security.core.Authentication;

public class ClientLogin implements UserLogin {
    @Override
    public String authenticate(Authentication user, JWTService jwtService) {
        return jwtService.generateToken(((User)user.getPrincipal()).getEmail());
    }
}
