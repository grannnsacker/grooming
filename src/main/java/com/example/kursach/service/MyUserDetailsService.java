package com.example.kursach.service;

import com.example.kursach.configuration.MyUserDetails;
import com.example.kursach.entity.User;
import com.example.kursach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof MyUserDetails) {
            return repository.findByName(((MyUserDetails) principal).getUsername()).orElse(null);
        }

        return null;
    }
}
