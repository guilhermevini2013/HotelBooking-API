package org.guilhermedev.hotelbooking.services.user.strategy;

import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserReadDTO;
import org.guilhermedev.hotelbooking.factories.dtos.UserDtoFactory;
import org.guilhermedev.hotelbooking.factories.role.RoleTypeFactory;
import org.guilhermedev.hotelbooking.factories.user.UserFactory;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.repositories.RoleRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.user.strategy.create.ClientCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class UserStrategyTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserStrategy userStrategy;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private User userForSaved;
    private UserRegisterDTO userRegisterDTO;
    private RoleType roleType;

    @BeforeEach
    void setUp() {
        userForSaved = UserFactory.getUser().build();
        userRegisterDTO = UserDtoFactory.getUserRegisterDTO();
        roleType = RoleTypeFactory.getRoleClient();
        when(roleRepository.findByAuthority("ROLE_CLIENT")).thenReturn(roleType);
        when(userRepository.save(userForSaved)).thenReturn(UserFactory.getUser().id(1l).build());
    }

    @Test
    @DisplayName("should save the user in the database when the roleType passed exists.")
    void createCase1() {
        try {
            ClientCreate userCreate = mock(ClientCreate.class);
            when(userCreate.create(userRegisterDTO, roleType, passwordEncoder))
                    .thenReturn(userForSaved);
            UserReadDTO userReadDTO = userStrategy.create(userCreate, userRegisterDTO);
            assertEquals(1l, userReadDTO.getId());
            verify(roleRepository, times(1)).findByAuthority("ROLE_CLIENT");
            verify(userCreate, times(1)).create(userRegisterDTO, roleType, passwordEncoder);
            verify(userRepository, times(1)).save(userForSaved);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}