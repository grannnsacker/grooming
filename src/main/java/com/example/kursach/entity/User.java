package com.example.kursach.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.roles = "User";
    }
}
