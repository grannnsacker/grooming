package com.example.kursach.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String date;
    private Boolean isBooked=false;
}
