package com.example.kursach.mappers;

import com.example.kursach.entity.User;
import com.example.kursach.reqmodels.UserDto;

public class UserMapper {
    public static User mapToDomain(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getPassword()
        );
    }
}
