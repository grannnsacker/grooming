package com.example.kursach.repository;


import com.example.kursach.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByOrderByIdAsc();

    List<Appointment> findAllByUserId(Long userId);
}
