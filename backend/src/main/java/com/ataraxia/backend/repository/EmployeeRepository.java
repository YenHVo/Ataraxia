package com.ataraxia.backend.repository;

import com.ataraxia.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUser_Id(Long userId);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    List<Employee> findByPositionContainingIgnoreCase(String position);
}
