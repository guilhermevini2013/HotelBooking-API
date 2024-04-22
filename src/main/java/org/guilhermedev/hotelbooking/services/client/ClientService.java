package org.guilhermedev.hotelbooking.services.client;

import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final UserRepository userRepository;

    public ClientService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void bookingHotel() {

    }
}
