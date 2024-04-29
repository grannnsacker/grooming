package com.example.kursach.contollers;

import com.example.kursach.entity.User;
import com.example.kursach.reqmodels.UserDto;
import com.example.kursach.mappers.UserMapper;
import com.example.kursach.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthController {
    private UserService service;

    @GetMapping("/welcome")
    public String welcome(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody User user) {
        service.addUser(user);
        return "User is saved";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("user") UserDto user, HttpServletRequest request) throws ServletException {
        if (!user.getPassword().equals(user.getConfirmPassword()))
        {
            return "redirect:/registration?error";
        }

        if (service.addUser(UserMapper.mapToDomain(user)))
        {
            request.login(user.getUsername(), user.getPassword());
            return "redirect:/welcome";
        }

        return "redirect:/registration?exists";
    }
}
