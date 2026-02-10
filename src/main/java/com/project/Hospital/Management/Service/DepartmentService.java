package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.DTO.departmentDTO;
import com.project.Hospital.Management.Entities.Department;
import com.project.Hospital.Management.Repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public departmentDTO toDeptDTO(Department d)
    {
        departmentDTO dto = new departmentDTO();
        dto.setDept_id(d.getDeptId());
        dto.setDept_name(d.getDeptName());
        dto.setLocation(d.getLocation());
        dto.setDept_head(d.getDeptHead());

        return dto;
    }


    public departmentDTO registerDepartment(departmentDTO dept) {
        try {
            Department d = new Department();
            d.setDeptName(dept.getDept_name());
            d.setLocation(dept.getLocation());
            d.setDeptHead(dept.getDept_head());

            Department saved = departmentRepo.save(d);
            return toDeptDTO(saved);

        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Department with this name already exists"
            );
        }
    }

    public String deleteDepartment(Long deptId)
    {
        if(!departmentRepo.existsById(deptId))
        {
            throw new RuntimeException("Department not found");
        }
        departmentRepo.deleteById(deptId);
        return "Department Deleted Successfully";
    }



    public departmentDTO updateDepartment(departmentDTO dept)
    {
        Department d = departmentRepo.findById(dept.getDept_id())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        d.setDeptName(dept.getDept_name());
        d.setLocation(dept.getLocation());
        d.setDeptHead(dept.getDept_head());

        departmentRepo.save(d);

        return toDeptDTO(d);
    }


    public List<departmentDTO> getAllDepartments()
    {
        return departmentRepo.findAll().stream().map(this::toDeptDTO).toList();
    }
}
