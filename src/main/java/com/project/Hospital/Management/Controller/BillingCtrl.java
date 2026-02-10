package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.DTO.BillingDTO;
import com.project.Hospital.Management.DTO.BillingReqDTO;
import com.project.Hospital.Management.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingCtrl {

    @Autowired
    private BillingService billingService;

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @PostMapping("/create")
    public BillingDTO createBill(@RequestBody BillingReqDTO reqDTO)
    {
        return billingService.createBill(reqDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @PutMapping("/pay/{billId}")
    public String payBill(@PathVariable Long billId)
    {
        billingService.updateBill(billId);
        return "Bill paid successfully";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @GetMapping("/{billId}")
    public BillingDTO getBillById(@PathVariable Long billId)
    {
        return billingService.getbybllid(billId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @GetMapping("/patient/name/{patientName}")
    public List<BillingDTO> getBillsByPatientName(@PathVariable String patientName)
    {
        return billingService.getbillsbyname(patientName);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    @GetMapping("/patient/id/{patientId}")
    public List<BillingDTO> getBillsByPatientId(@PathVariable Long patientId)
    {
        return billingService.getbillbyPatientid(patientId);
    }

}
