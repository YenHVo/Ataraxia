package com.ataraxia.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.ataraxia.backend.entity.Employee;
import com.ataraxia.backend.service.EmployeeService;
import java.util.List;  

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/position/{position}")
    public List<Employee> getEmployeesByPosition(@PathVariable String position) {
        return employeeService.getEmployeesByPosition(position);
    }

    @GetMapping("/lastname/{lastName}")
    public List<Employee> getEmployeesByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeesByLastName(lastName);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    
}
