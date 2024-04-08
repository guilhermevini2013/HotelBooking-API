package org.guilhermedev.hotelbooking.services.user.strategy;

import org.guilhermedev.hotelbooking.dto.user.insert.UserLoginDTO;
import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.dto.user.read.UserReadDTO;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.repositories.RoleRepository;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.guilhermedev.hotelbooking.services.user.strategy.create.UserCreate;
import org.guilhermedev.hotelbooking.services.user.strategy.login.UserLogin;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserStrategy {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserStrategy(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserReadDTO create(UserCreate userCreate, UserRegisterDTO userRegisterDTO) {
        RoleType role = roleRepository.findByAuthority("ROLE_" + userRegisterDTO.typeUser());
        User user = userCreate.create(userRegisterDTO, role, passwordEncoder);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email ou identidade ja existentes");
        }
        return new UserReadDTO(user);
    }

    public String login(UserLogin userLogin, UserLoginDTO userLoginDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.credential(), userLoginDTO.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        return userLogin.authenticate(authenticate, jwtService);
    }
}
