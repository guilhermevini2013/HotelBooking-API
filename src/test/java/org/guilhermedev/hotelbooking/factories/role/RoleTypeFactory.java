package org.guilhermedev.hotelbooking.factories.role;

import org.guilhermedev.hotelbooking.models.user.RoleType;

public class RoleTypeFactory {
    public static RoleType getRoleClient() {
        return new RoleType("ROLE_CLIENT");
    }
}
