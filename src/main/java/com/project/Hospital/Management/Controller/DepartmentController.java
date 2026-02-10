package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.DTO.departmentDTO;
import com.project.Hospital.Management.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public departmentDTO registerDept(@RequestBody departmentDTO dto)
    {
        return  departmentService.registerDepartment(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<departmentDTO> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public String deletedept(@RequestParam Long deptid)
    {
        return departmentService.deleteDepartment(deptid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public departmentDTO updateDept(@PathVariable Long deptId,
                                    @RequestBody departmentDTO dept) {
        dept.setDept_id(deptId);
        return departmentService.updateDepartment(dept);
    }
}
