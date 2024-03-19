package org.guilhermedev.hotelbooking.services.user;

import jakarta.persistence.EntityNotFoundException;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {
        return userRepository.findByCredential(credential).orElseThrow(() -> new EntityNotFoundException("Credential not found"));
    }
}
