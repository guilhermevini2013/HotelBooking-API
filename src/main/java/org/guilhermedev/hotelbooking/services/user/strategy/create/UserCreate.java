package org.guilhermedev.hotelbooking.services.user.strategy.create;

import org.guilhermedev.hotelbooking.dto.user.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserCreate {
    User create(UserRegisterDTO clientRegisterDTO, RoleType roleType, PasswordEncoder passwordEncoder);
}
