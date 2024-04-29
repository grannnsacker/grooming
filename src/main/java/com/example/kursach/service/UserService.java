package com.example.kursach.service;


import com.example.kursach.entity.User;
import com.example.kursach.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public Boolean addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!repository.existsByName(user.getName())) {
            repository.save(user);
            return true;
        }
        return false;
    }


}