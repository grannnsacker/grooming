package com.example.kursach.service;

import com.example.kursach.entity.Appointment;
import com.example.kursach.entity.User;
import com.example.kursach.repository.AppointmentRepository;
import com.example.kursach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public List<Appointment> findAll(){
        return appointmentRepository.findAllByOrderByIdAsc();
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null){
            User user = appointment.getUser();
            user.getAppointments().remove(appointment);
            userRepository.save(user);
            appointment.setIsBooked(false);
            appointment.setUser(null);
            appointmentRepository.save(appointment);
        }
    }

    public Appointment findById(Long id){
        return appointmentRepository.findById(id).orElse(null);
    }

    public void connectAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment == null) {
            return;
        }
        User user = myUserDetailsService.getCurrentUser();
        if (user != null) {
            user.getAppointments().add(appointment);
            appointment.setUser(user);
            appointment.setIsBooked(true);
            appointmentRepository.save(appointment);
        }
    }

    public User getCurrentUser(){
        return myUserDetailsService.getCurrentUser();
    }

    public List<Appointment> getCurrentUserAppointments(Long userId) {
        return appointmentRepository.findAllByUserId(userId);
    }


    public void deleteAll() {
        appointmentRepository.deleteAll();
    }
}
