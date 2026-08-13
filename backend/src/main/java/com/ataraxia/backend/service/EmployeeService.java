package com.ataraxia.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ataraxia.backend.entity.Employee;
import com.ataraxia.backend.entity.User;
import com.ataraxia.backend.repository.UserRepository;
import com.ataraxia.backend.repository.EmployeeRepository;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
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
        Long userId = employee.getUser().getId();

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException(
                    "User not found with id: " + userId
            ));

        employee.setUser(user);
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
