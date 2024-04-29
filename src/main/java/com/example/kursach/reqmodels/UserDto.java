package com.example.kursach.reqmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto{
    private String username;
    private String password;
    private String confirmPassword;
}
