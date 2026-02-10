package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.DTO.appointmentDTO;
import com.project.Hospital.Management.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/appointment")
public class AppointmentCtrl {

    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @PostMapping
    public appointmentDTO takeAppointment( @RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDate appointmentDate, @RequestParam LocalTime appointmentTime)
    {
        return appointmentService.takeAppointment(doctorId, patientId, appointmentDate, appointmentTime);
    }
}
