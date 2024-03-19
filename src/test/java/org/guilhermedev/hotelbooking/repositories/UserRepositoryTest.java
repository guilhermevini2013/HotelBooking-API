package org.guilhermedev.hotelbooking.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.Enterprise;
import org.guilhermedev.hotelbooking.models.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.LENIENT)
class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;
    private String emailExist = "Guilherme@gmail";
    private String cnpjExist = "58.084.569/0001-62";

    @BeforeEach
    void setUp() {
        when(userRepository.findByCredential(emailExist)).thenReturn(Optional.of(new Client.Builder().build()));
        when(userRepository.findByCredential(cnpjExist)).thenReturn(Optional.of(new Enterprise.Builder().build()));
    }

    @Test
    void findByCredentialShouldReturnEntityNotFoundExceptionWhenCredentialIsCNPJButNotExistInDataBase() {
        String cnpjNotExist = "10.900.519/0011-12";
        assertThrows(EntityNotFoundException.class, () -> {
            userRepository.findByCredential(cnpjNotExist)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        });
    }

    @Test
    void findByCredentialShouldReturnInstanceEnterpriseWhenCredentialIsCNPJAndExistInDataBase() {
        User user = userRepository.findByCredential(cnpjExist)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        assertTrue(user instanceof Enterprise);
    }

    @Test
    void findByCredentialShouldReturnEntityNotFoundExceptionWhenCredentialIsEmailButNotExistInDataBase() {
        String emailNotExist = "Vinicius@gmail";
        assertThrows(EntityNotFoundException.class, () -> {
            userRepository.findByCredential(emailNotExist)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        });
    }

    @Test
    void findByCredentialShouldReturnInstanceClientWhenCredentialIsEmailAndExistInDataBase() {
        User user = userRepository.findByCredential(emailExist)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        assertTrue(user instanceof Client);
    }
}