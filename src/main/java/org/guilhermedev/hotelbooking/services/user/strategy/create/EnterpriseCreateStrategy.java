package org.guilhermedev.hotelbooking.services.user.strategy.create;

import org.guilhermedev.hotelbooking.dto.user.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class EnterpriseCreateStrategy implements UserCreate {
    @Transactional
    public User create(UserRegisterDTO enterpriseRegisterDTO, RoleType roleType, PasswordEncoder passwordEncoder) {
        return new Enterprise.Builder()
                .email(enterpriseRegisterDTO.email())
                .contact(enterpriseRegisterDTO.contact())
                .password(passwordEncoder.encode(enterpriseRegisterDTO.password()))
                .identity(enterpriseRegisterDTO.identity())
                .name(enterpriseRegisterDTO.name())
                .roles(Set.of(roleType))
                .build();
    }
}
