package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.DTO.BillingDTO;
import com.project.Hospital.Management.DTO.BillingReqDTO;
import com.project.Hospital.Management.Entities.Billing;
import com.project.Hospital.Management.Entities.Patient;
import com.project.Hospital.Management.Repo.BillingRepo;
import com.project.Hospital.Management.Repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class BillingService {

    @Autowired
    BillingRepo billingRepo;

    @Autowired
    PatientRepo patientRepo;

    //CREATION
    public BillingDTO createBill(BillingReqDTO req)
    {
        Patient patient = patientRepo.findById(req.getPatientId()).
                orElseThrow(()->new RuntimeException("Patient Not Found"));
        BigDecimal room = req.getRoomCharges() == null ? BigDecimal.ZERO : req.getRoomCharges();
        BigDecimal med = req.getMedicineCharges() == null ? BigDecimal.ZERO : req.getMedicineCharges();
        BigDecimal doc = req.getDoctorCharges() == null ? BigDecimal.ZERO : req.getDoctorCharges();

        Billing bill = new Billing();
        bill.setPatientname(patient.getName());
        bill.setPatient(patient);
        bill.setBillDate(req.getBillDate());
        bill.setRoomCharges(room);
        bill.setMedicineCharges(med);
        bill.setDoctorCharges(doc);
        bill.setTotalCharges(room.add(med).add(doc));
        bill.setStatus(false);

        billingRepo.save(bill);

        BillingDTO dto = new BillingDTO();

        dto.setBillId(bill.getBillId());
        dto.setPatientname(bill.getPatientname());
        dto.setPatientId(bill.getPatient().getPatientId());
        dto.setBillDate(bill.getBillDate());
        dto.setRoomCharges(bill.getRoomCharges());
        dto.setMedicineCharges(bill.getMedicineCharges());
        dto.setDoctorCharges(bill.getDoctorCharges());
        dto.setTotalCharges(bill.getTotalCharges());
        dto.setStatus(bill.getStatus());

        return dto;
    }

    //PAYEMENT UPDATION
    public boolean updateBill(Long billId)
    {
        Billing bill = billingRepo.findById(billId).
                orElseThrow(()->new RuntimeException("Bill  Not Found"));
        bill.setStatus(true);
        billingRepo.save(bill);

        return true;
    }

    //GETBILLBYID
    public BillingDTO getbybllid(Long Bill_id)
    {
        Billing bill = billingRepo.findById(Bill_id)
                .orElseThrow(()->new RuntimeException("Bill Not Found"));
        BillingDTO dto = new BillingDTO();
        dto.setBillId(bill.getBillId());
        dto.setPatientname(bill.getPatientname());
        dto.setPatientId(bill.getPatient().getPatientId());
        dto.setBillDate(bill.getBillDate());
        dto.setRoomCharges(bill.getRoomCharges());
        dto.setMedicineCharges(bill.getMedicineCharges());
        dto.setTotalCharges(bill.getTotalCharges());
        dto.setStatus(bill.getStatus());
        return dto;
    }

    //GetBillsByName
    public List<BillingDTO> getbillsbyname(String patientname)
    {
         List<Billing> bill = billingRepo.findByPatientname(patientname);
         if (bill.isEmpty())
         {
             throw new RuntimeException("No Bill Found");
         }
         List<BillingDTO> dtolist = new ArrayList<>();
         for(Billing b : bill)
         {
             BillingDTO dto = new BillingDTO();
             dto.setBillId(b.getBillId());
             dto.setPatientname(b.getPatientname());
             dto.setPatientId(b.getPatient().getPatientId());
             dto.setBillDate(b.getBillDate());
             dto.setRoomCharges(b.getRoomCharges());
             dto.setMedicineCharges(b.getMedicineCharges());
             dto.setDoctorCharges(b.getDoctorCharges());
             dto.setTotalCharges(b.getTotalCharges());
             dto.setStatus(b.getStatus());
             dtolist.add(dto);
         }
         return dtolist;
    }

    //GetBillByPatientId
    public List<BillingDTO> getbillbyPatientid(Long patientid)
    {

        List<Billing> bill = billingRepo.findByPatient_PatientId(patientid);
        if(bill.isEmpty())
        {
            throw new RuntimeException("No Bill Found");
        }
        List<BillingDTO> dto = new ArrayList<>();
        for(Billing b : bill)
        {
            BillingDTO dto1 = new BillingDTO();
            dto1.setBillId(b.getBillId());
            dto1.setPatientname(b.getPatientname());
            dto1.setPatientId(b.getPatient().getPatientId());
            dto1.setBillDate(b.getBillDate());
            dto1.setRoomCharges(b.getRoomCharges());
            dto1.setMedicineCharges(b.getMedicineCharges());
            dto.add(dto1);
        }
        return dto;
    }
}
