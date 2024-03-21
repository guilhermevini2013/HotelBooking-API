package org.guilhermedev.hotelbooking.factories.dtos;

import org.guilhermedev.hotelbooking.dto.user.insert.ContactDTO;
import org.guilhermedev.hotelbooking.dto.user.insert.UserRegisterDTO;
import org.guilhermedev.hotelbooking.models.user.TypeUser;

import java.time.Instant;
import java.util.Date;

public class UserDtoFactory {
    public static UserRegisterDTO getUserRegisterDTO() {
        return new UserRegisterDTO("Guilherme", "Guilherme@gmail", "123456", "49486479999",
                Date.from(Instant.now()), "MASCULINO",
                new ContactDTO(1l, "1234", "Guilhermevini2013@hotmail.com"), TypeUser.CLIENT);
    }
}
