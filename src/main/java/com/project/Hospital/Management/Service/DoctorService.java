package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.DTO.doctorDTO;
import com.project.Hospital.Management.Entities.Department;
import com.project.Hospital.Management.Entities.Doctor;
import com.project.Hospital.Management.Repo.DepartmentRepo;
import com.project.Hospital.Management.Repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public doctorDTO registerDoctor(doctorDTO doctorDTO)
    {
        if (doctorRepo.existsByEmailId(doctorDTO.getEmail_id())) {
            throw new RuntimeException("Doctor with this email already exists");
        }

        if (doctorRepo.existsByContactNo(doctorDTO.getContact_no())) {
            throw new RuntimeException("Doctor with this contact number already exists");
        }

        Department dept = departmentRepo.findById(doctorDTO.getDepartmentId()).orElseThrow(()->
                new RuntimeException("Department Not Found"));

        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setDepartment(dept);
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setContactNo(doctorDTO.getContact_no());
        doctor.setEmailId(doctorDTO.getEmail_id());
        doctor.setAvailability(true);
        doctorRepo.save(doctor);

        doctorDTO dto = new doctorDTO();


        dto.setDoctorId(doctor.getDoctorId());
        dto.setAvailability(doctor.getAvailability());
        dto.setName(doctor.getName());
        dto.setDoctorId(doctor.getDoctorId());
        dto.setDepartmentId(dept.getDeptId());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setContact_no(doctor.getContactNo());
        dto.setEmail_id(doctor.getEmailId());

        return dto;
    }

    public doctorDTO getDoctorById(Long id)
    {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorDTO dto = new doctorDTO();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setAvailability(doctor.getAvailability());
        dto.setName(doctor.getName());
        dto.setDoctorId(doctor.getDoctorId());
        dto.setDepartmentId(doctor.getDepartment().getDeptId());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setContact_no(doctor.getContactNo());
        dto.setEmail_id(doctor.getEmailId());

         return dto;
    }

    public doctorDTO updateDoctor(Long id, doctorDTO req)
    {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(req.getName());
        doctor.setSpecialization(req.getSpecialization());
        doctor.setContactNo(req.getContact_no());
        doctor.setEmailId(req.getEmail_id());
        doctorRepo.save(doctor);

        return getDoctorById(id);
    }

    public doctorDTO setAvaliability(Long id, boolean availability)
    {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setAvailability(availability);

        doctorRepo.save(doctor);
        return getDoctorById(id);
    }

    public boolean deleteDoctorById(Long id)
    {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorRepo.delete(doctor);
        return true;
    }

    public List<doctorDTO> listofAllDoctors()
    {
        return doctorRepo.findAll().stream().map(d->{
            doctorDTO dto = new doctorDTO();
            dto.setDoctorId(d.getDoctorId());
            dto.setAvailability(d.getAvailability());
            dto.setName(d.getName());
            dto.setDepartmentId(d.getDepartment().getDeptId());
            dto.setSpecialization(d.getSpecialization());
            dto.setContact_no(d.getContactNo());
            dto.setEmail_id(d.getEmailId());
            return dto;
        }).toList();
    }

    public List<doctorDTO> getDoctorsByDepartment(Long deptId) {

        return doctorRepo.findByDepartment_DeptId(deptId)
                .stream()
                .map(d -> {
                    doctorDTO dto = new doctorDTO();
                    dto.setDoctorId(d.getDoctorId());
                    dto.setName(d.getName());
                    dto.setSpecialization(d.getSpecialization());
                    dto.setDepartmentId(d.getDepartment().getDeptId());
                    dto.setAvailability(d.getAvailability());
                    return dto;
                })
                .toList();
    }

    public List<doctorDTO> getAvailableDoctors() {

        return doctorRepo.findByAvailability(true)
                .stream()
                .map(d -> {
                    doctorDTO dto = new doctorDTO();
                    dto.setDoctorId(d.getDoctorId());
                    dto.setName(d.getName());
                    dto.setSpecialization(d.getSpecialization());
                    dto.setDepartmentId(d.getDepartment().getDeptId());
                    dto.setAvailability(d.getAvailability());
                    return dto;
                })
                .toList();
    }

    public List<doctorDTO> searchDoctorsBySpecialization(String specialization) {
        return doctorRepo.findBySpecializationContainingIgnoreCase(specialization)
                .stream()
                .map(this::toDTO)
                .toList();
    }




    private doctorDTO toDTO(Doctor doctor) {
        doctorDTO dto = new doctorDTO();

        dto.setDoctorId(doctor.getDoctorId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setContact_no(doctor.getContactNo());
        dto.setEmail_id(doctor.getEmailId());

        // Department may be null (avoid NullPointerException)
        if (doctor.getDepartment() != null) {
            dto.setDepartmentId(doctor.getDepartment().getDeptId());
        }

        dto.setAvailability(doctor.getAvailability());

        return dto;
    }


}
