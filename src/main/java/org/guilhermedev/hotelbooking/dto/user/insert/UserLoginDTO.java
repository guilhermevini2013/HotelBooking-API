package org.guilhermedev.hotelbooking.dto.user.insert;

import org.guilhermedev.hotelbooking.models.user.TypeUser;

public record UserLoginDTO(String credential,
                           String password,
                           TypeUser typeUser) {
}
