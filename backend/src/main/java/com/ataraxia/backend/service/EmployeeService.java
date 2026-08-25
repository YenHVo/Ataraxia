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

    public Employee getEmployeeByUserId(Long userId) {
        return employeeRepository.findByUser_Id(userId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found with user id: " + userId
        ));
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found with email: " + email
        ));
    }

    public Employee createEmployee(Employee employee) {
        User user = userRepository.findById(employee.getUser().getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found with id: " + id
            ));

        String newEmail = updatedEmployee.getEmail();
        if (!existingEmployee.getEmail().equals(newEmail)) {
            // Check Employee emails
            employeeRepository.findByEmail(newEmail)
                .ifPresent(employee -> {
                    if (!employee.getId().equals(id)) {
                        throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Employee with email " + newEmail + " already exists"
                        );
                    }
                });

            // Check User emails
            userRepository.findByEmail(newEmail)
                .ifPresent(user -> {
                    if (!user.getId().equals(existingEmployee.getUser().getId())) {
                        throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "User with email " + newEmail + " already exists"
                        );
                    }
                });

            // Update Employee email
            existingEmployee.setEmail(newEmail);

            // Update associated User email
            if (existingEmployee.getUser() != null) {
                existingEmployee.getUser().setEmail(newEmail);
            }
        }
            
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
