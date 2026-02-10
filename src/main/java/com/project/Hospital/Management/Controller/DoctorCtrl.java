package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.DTO.doctorDTO;
import com.project.Hospital.Management.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorCtrl {

    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<doctorDTO> listofAllDoctors()
    {
        return doctorService.listofAllDoctors();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/department/{deptid}")
    public List<doctorDTO> getDoctorsByDepartment(@PathVariable Long deptid)
    {
        return doctorService.getDoctorsByDepartment(deptid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public List<doctorDTO> searchDoctorsBySpecialization(@RequestParam String specialization)
    {
        return doctorService.searchDoctorsBySpecialization(specialization);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public doctorDTO getDoctorById(@PathVariable Long id)
    {
        return doctorService.getDoctorById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/available")
    public List<doctorDTO> getAvailableDoctors()
    {
        return doctorService.getAvailableDoctors();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public boolean deleteDoctor(@PathVariable Long id)
    {
        return doctorService.deleteDoctorById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}/availability")
    public doctorDTO setAvailability(@PathVariable Long id, @RequestParam boolean available)
    {
        return doctorService.setAvaliability(id, available);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public doctorDTO updateDoctor(@PathVariable Long id, @RequestBody doctorDTO req)
    {
        return doctorService.updateDoctor(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public doctorDTO registerDoctor(@RequestBody doctorDTO req)
    {
        return doctorService.registerDoctor(req);
    }
}
