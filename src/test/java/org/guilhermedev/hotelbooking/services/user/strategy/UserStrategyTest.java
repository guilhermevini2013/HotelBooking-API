package org.guilhermedev.hotelbooking.services.user.strategy;

import org.guilhermedev.hotelbooking.repositories.RoleRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class UserStrategyTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTService jwtService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createShouldEncodePasswordAndSaveUserInDataBaseAndReturnUserReadDTO() {

    }
}