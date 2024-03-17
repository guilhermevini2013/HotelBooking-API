package org.guilhermedev.hotelbooking.services.user;

import org.guilhermedev.hotelbooking.dto.user.UserReadDTO;
import org.guilhermedev.hotelbooking.dto.user.UserRegisterDTO;
import org.guilhermedev.hotelbooking.services.user.strategy.create.ClientCreateStrategy;
import org.guilhermedev.hotelbooking.services.user.strategy.create.EnterpriseCreateStrategy;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserStrategy userStrategy;

    public UserService(UserStrategy userStrategy) {
        this.userStrategy = userStrategy;
    }

    public UserReadDTO register(UserRegisterDTO userRegisterDTO) {
        switch (userRegisterDTO.typeUser()) {
            case ENTERPRISE -> {
                return userStrategy.create(new EnterpriseCreateStrategy(), userRegisterDTO);
            }
            case CLIENT -> {
                return userStrategy.create(new ClientCreateStrategy(), userRegisterDTO);
            }
            default -> {
                return null;
            }
        }
    }
}
