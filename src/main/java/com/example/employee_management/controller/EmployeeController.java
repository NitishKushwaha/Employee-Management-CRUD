package com.example.employee_management.controller;

import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        // Make sure the department exists
        Long deptId = employee.getDepartment().getDepartmentId();
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + deptId));

        employee.setDepartment(department);
        employee.setCreatedOn(LocalDateTime.now());
        employee.setModifiedOn(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        // Update fields
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setActive(employeeDetails.getActive());

        // Update department if provided
        if (employeeDetails.getDepartment() != null) {
            Long deptId = employeeDetails.getDepartment().getDepartmentId();
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found with id " + deptId));
            employee.setDepartment(department);
        }

        employee.setModifiedOn(LocalDateTime.now());

        return employeeRepository.save(employee);
    }
}

