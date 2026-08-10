package com.ataraxia.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.Employee;
import com.ataraxia.backend.repository.EmployeeRepository;
import java.util.List;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found with id: " + id
        ));
    }

    public List<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Employee> getEmployeesByPosition(String position) {
        return employeeRepository.findByPositionContainingIgnoreCase(position);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found with id: " + id
            ));

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setAddress(updatedEmployee.getAddress());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    
}
