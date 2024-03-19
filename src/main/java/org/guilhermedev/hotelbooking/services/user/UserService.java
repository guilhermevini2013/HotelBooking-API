package org.guilhermedev.hotelbooking.services.user;

import org.guilhermedev.hotelbooking.dto.user.insert.UserLoginDTO;
import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserLoadDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserReadDTO;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.guilhermedev.hotelbooking.services.user.strategy.UserStrategy;
import org.guilhermedev.hotelbooking.services.user.strategy.create.ClientCreate;
import org.guilhermedev.hotelbooking.services.user.strategy.create.EnterpriseCreate;
import org.guilhermedev.hotelbooking.services.user.strategy.login.ClientLogin;
import org.guilhermedev.hotelbooking.services.user.strategy.login.EnterpriseLogin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserStrategy userStrategy;

    public UserService(UserStrategy userStrategy) {
        this.userStrategy = userStrategy;
    }

    @Transactional
    public UserReadDTO register(UserRegisterDTO userRegisterDTO) {
        switch (userRegisterDTO.typeUser()) {
            case ENTERPRISE -> {
                return userStrategy.create(new EnterpriseCreate(), userRegisterDTO);
            }
            case CLIENT -> {
                return userStrategy.create(new ClientCreate(), userRegisterDTO);
            }
            default -> throw new ResourceNotFoundException("Type user no exist");
        }
    }

    @Transactional
    public UserLoadDTO login(UserLoginDTO userLoginDTO) {
        String token;
        switch (userLoginDTO.typeUser()) {
            case ENTERPRISE -> token = userStrategy.login(new EnterpriseLogin(), userLoginDTO);
            case CLIENT -> token = userStrategy.login(new ClientLogin(), userLoginDTO);
            default -> throw new ResourceNotFoundException("Type user no exist");
        }
        return new UserLoadDTO(token);
    }
}
