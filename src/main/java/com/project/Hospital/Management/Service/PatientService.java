package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.DTO.patientDTO;
import com.project.Hospital.Management.Entities.Doctor;
import com.project.Hospital.Management.Entities.Patient;
import com.project.Hospital.Management.Repo.DoctorRepo;
import com.project.Hospital.Management.Repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    public patientDTO registerPatient(patientDTO req)
    {
        Doctor doctor = doctorRepo.findById(req.getDoctorID())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = new Patient();
        patient.setName(req.getName());
        patient.setAge(req.getAge());
        patient.setGender(req.getGender());
        patient.setContactNo(req.getContact_no());
        patient.setAddress(req.getAddress());
        patient.setEmailId(req.getEmail_id());
        patient.setAdmissionDate(LocalDateTime.now());
        patient.setBloodGroup(req.getBlood_group());
        patient.setDoctor(doctor);

        patientRepo.save(patient);

        patientDTO dto = new patientDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setContact_no(patient.getContactNo());
        dto.setAddress(patient.getAddress());
        dto.setEmail_id(patient.getEmailId());
        dto.setAdmission_date(patient.getAdmissionDate());
        dto.setBlood_group(patient.getBloodGroup());
        dto.setDoctorname(doctor.getName());
        dto.setDoctorID(doctor.getDoctorId());

        return dto;
    }

    public patientDTO getPatientById(Long id)
    {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientDTO dto = new patientDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setContact_no(patient.getContactNo());
        dto.setAddress(patient.getAddress());
        dto.setEmail_id(patient.getEmailId());
        dto.setAdmission_date(patient.getAdmissionDate());
        dto.setRelease_date(patient.getReleaseDate());
        dto.setBlood_group(patient.getBloodGroup());
        dto.setDoctorname(patient.getDoctor().getName());
        dto.setDoctorID(patient.getDoctor().getDoctorId());

        return dto;
    }

    public patientDTO updatePatient(Long id, patientDTO req)
    {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(req.getName());
        patient.setAge(req.getAge());
        patient.setGender(req.getGender());
        patient.setContactNo(req.getContact_no());
        patient.setAddress(req.getAddress());
        patient.setEmailId(req.getEmail_id());
        patient.setBloodGroup(req.getBlood_group());

        patientRepo.save(patient);

        return getPatientById(patient.getPatientId());
    }

    public patientDTO dischargePatient(Long id)
    {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setReleaseDate(LocalDateTime.now());

        patientRepo.save(patient);

        return getPatientById(id);
    }


    public boolean deletePatient(Long id)
    {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientRepo.delete(patient);

        return true;
    }

    public List<patientDTO> getAllPatients()
    {
        return patientRepo.findAll().stream().map(p -> {
            patientDTO dto = new patientDTO();
            dto.setPatientId(p.getPatientId());
            dto.setName(p.getName());
            dto.setAge(p.getAge());
            dto.setGender(p.getGender());
            dto.setDoctorname(p.getDoctor().getName());
            dto.setDoctorID(p.getDoctor().getDoctorId());
            return dto;
        }).toList();
    }

    public List<patientDTO> getPatientsByDoctor(Long doctorId)
    {
        List<Patient> patients = patientRepo.findByDoctor_DoctorId(doctorId);

        return patients.stream().map(p -> {
            patientDTO dto = new patientDTO();
            dto.setPatientId(p.getPatientId());
            dto.setName(p.getName());
            dto.setAge(p.getAge());
            dto.setDoctorname(p.getDoctor().getName());
            dto.setDoctorID(p.getDoctor().getDoctorId());
            return dto;
        }).toList();
    }

}
