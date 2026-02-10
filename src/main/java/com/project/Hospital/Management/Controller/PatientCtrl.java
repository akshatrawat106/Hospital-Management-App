package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.DTO.patientDTO;
import com.project.Hospital.Management.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientCtrl {

    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @PostMapping
    public patientDTO registerPatient(@RequestBody patientDTO req) {
        return patientService.registerPatient(req);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @GetMapping("/{id}")
    public patientDTO getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @GetMapping
    public List<patientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @GetMapping("/doctor/{doctorId}")
    public List<patientDTO> getPatientsByDoctor(@RequestParam Long doctorId)
    {
        return patientService.getPatientsByDoctor(doctorId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @PutMapping("/{id}")
    public patientDTO updatePatient(@PathVariable Long id,@RequestBody patientDTO req)
    {
        return patientService.updatePatient(id, req);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @PutMapping("/{id}/discharge")
    public patientDTO dischargePatient(@PathVariable Long id)
    {
        return patientService.dischargePatient(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR','RECEPTIONIST')")
    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
