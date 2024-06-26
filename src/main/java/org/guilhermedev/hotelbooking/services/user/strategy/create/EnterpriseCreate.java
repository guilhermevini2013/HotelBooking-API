package org.guilhermedev.hotelbooking.services.user.strategy.create;

import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EnterpriseCreate implements UserCreate {
    public User create(UserRegisterDTO enterpriseRegisterDTO, RoleType roleType, PasswordEncoder passwordEncoder) {
        return new Enterprise.Builder()
                .password(passwordEncoder.encode(enterpriseRegisterDTO.password()))
                .identity(enterpriseRegisterDTO.identity())
                .name(enterpriseRegisterDTO.name())
                .email(enterpriseRegisterDTO.email())
                .phone(enterpriseRegisterDTO.numberPhone())
                .roles(Set.of(roleType))
                .build();
    }
}
