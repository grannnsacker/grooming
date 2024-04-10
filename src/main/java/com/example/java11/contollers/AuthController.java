package com.example.java11.contollers;

import com.example.java11.entity.User;
import com.example.java11.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class AuthController {
    private UserService service;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody User user) {
        service.addUser(user);
        return "User is saved";
    }
}
