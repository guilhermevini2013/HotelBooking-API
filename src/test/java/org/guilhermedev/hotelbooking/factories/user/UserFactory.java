package org.guilhermedev.hotelbooking.factories.user;

import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.models.user.RoleType;
import org.guilhermedev.hotelbooking.models.user.User;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

public class UserFactory {
    public static Client.Builder getUser(){
        return new Client.Builder()
                .email("Guilherme")
                .contact(new ContactDTO(1l, "1234", "Guilhermevini2013@hotmail.com"))
                .password("$2a$12$6644FpLWEIpgKWZB1PpsE.fXLjePw3yRlj2VsPpV/VHuXgILub.Ym")
                .identity("49486479999")
                .name("Guilherme")
                .roles(Set.of(new RoleType("ROLE_CLIENT")))
                .dateOfBirth(Date.from(Instant.now()))
                .gender("Masculino");
    }
}
