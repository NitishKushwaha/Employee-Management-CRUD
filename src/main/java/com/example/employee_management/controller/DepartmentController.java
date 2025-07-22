package com.example.employee_management.controller;

import com.example.employee_management.entity.Department;
import com.example.employee_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        department.setCreatedOn(LocalDateTime.now());
        department.setModifiedOn(LocalDateTime.now());
        return departmentRepository.save(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));

        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setActive(departmentDetails.getActive());
        department.setModifiedOn(LocalDateTime.now());

        return departmentRepository.save(department);
    }
}
