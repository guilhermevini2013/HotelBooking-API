package org.guilhermedev.hotelbooking.services.user.strategy.create;

import org.guilhermedev.hotelbooking.dto.user.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class ClientCreateStrategy implements UserCreate {
    @Transactional
    public User create(UserRegisterDTO clientRegisterDTO, RoleType roleType, PasswordEncoder passwordEncoder) {
        return new Client.Builder()
                .email(clientRegisterDTO.email())
                .contact(clientRegisterDTO.contact())
                .password(passwordEncoder.encode(clientRegisterDTO.password()))
                .identity(clientRegisterDTO.identity())
                .name(clientRegisterDTO.name())
                .roles(Set.of(roleType))
                .dateOfBirth(clientRegisterDTO.dateOfBirth())
                .gender(clientRegisterDTO.gender())
                .build();
    }
}
