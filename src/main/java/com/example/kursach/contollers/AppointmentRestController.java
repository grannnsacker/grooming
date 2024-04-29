package com.example.kursach.contollers;


import com.example.kursach.entity.Appointment;
import com.example.kursach.entity.User;
import com.example.kursach.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AppointmentRestController {
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/get_all_appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAll();
    }

    @PostMapping("/add_appointment")
    public void addAppointment(@RequestBody Appointment appointment) {
        appointmentService.save(appointment);
    }

    @PutMapping("/connect_appointment_to_user")
    public void connectAppointment(@RequestParam("appointmentId") Long appointmentId) {
        appointmentService.connectAppointment(appointmentId);
    }

    @GetMapping("/get_current_user")
    public ResponseEntity<User> connectAppointment() {
        User user = appointmentService.getCurrentUser();
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(user);
    }


    @GetMapping("/get_current_appointments")
    public List<Appointment> getCurrentUserAppointments() {
        User user = appointmentService.getCurrentUser();
        if (user != null)
            return appointmentService.getCurrentUserAppointments(user.getId());
        return null;
    }

    @DeleteMapping("/delete_appointments")
    public void deleteAllAppointment() {
        appointmentService.deleteAll();
    }

    @PostMapping("/delete_appointment_from_current")
    public void deleteAppointment(@RequestParam("appointmentId") Long appointmentId) {
        appointmentService.delete(appointmentId);
    }


}
