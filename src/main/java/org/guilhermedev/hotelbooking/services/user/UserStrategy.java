package org.guilhermedev.hotelbooking.services.user;

import org.guilhermedev.hotelbooking.dto.user.UserReadDTO;
import org.guilhermedev.hotelbooking.dto.user.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.repositories.RoleRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.user.strategy.create.UserCreate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserStrategy {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserStrategy(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserReadDTO create(UserCreate userCreate, UserRegisterDTO userRegisterDTO) {
        RoleType role = roleRepository.findByAuthority("ROLE_" + userRegisterDTO.typeUser());
        User user = userCreate.create(userRegisterDTO, role, passwordEncoder);
        user = userRepository.save(user);
        return new UserReadDTO(user);
    }
}
