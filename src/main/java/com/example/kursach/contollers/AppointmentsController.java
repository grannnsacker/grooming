package com.example.kursach.contollers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AppointmentsController {
    @GetMapping("/appointments")
    public String appointments(Model model) {
        return "appointments";
    }
}
