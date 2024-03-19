package org.guilhermedev.hotelbooking.services.user.strategy.login;

import org.guilhermedev.hotelbooking.dto.user.insert.UserLoginDTO;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;

public interface UserLogin {
    String authenticate(Authentication user, JWTService jwtService);
}
