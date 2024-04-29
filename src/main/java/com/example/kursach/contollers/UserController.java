package com.example.kursach.contollers;


import com.example.kursach.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;


}
