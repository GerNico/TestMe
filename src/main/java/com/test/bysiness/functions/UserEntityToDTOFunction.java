package com.test.bysiness.functions;

import com.test.bysiness.dto.UserDTO;
import com.test.bysiness.entities.UserEntity;

import java.util.function.Function;

public class UserEntityToDTOFunction implements Function<UserEntity,UserDTO> {
    @Override
    public UserDTO apply(UserEntity o) {
        return null;
    }
}
