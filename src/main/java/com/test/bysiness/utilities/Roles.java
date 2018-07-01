package com.test.bysiness.utilities;

import java.util.Arrays;

public enum Roles {
    User("User"), Admin("Admin"), Author("Author"), Moderator("Moderator");

    String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isValid(String role) {
        return Arrays.stream(Roles.values())
                .map(Roles::getName)
                .anyMatch(roleString -> roleString.equals(role));
    }
}
