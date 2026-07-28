package com.ataraxia.backend.config;

import com.ataraxia.backend.entity.Role;
import com.ataraxia.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role("ADMIN", "Administrator role with full access"));

                roleRepository.save(new Role("GUEST", "Standard user role with limited access"));

                roleRepository.save(new Role("MANAGER", "Manager role with moderate access"));

                roleRepository.save(new Role("SUPPLIER", "Supplier role with access to supplier-related features"));

                roleRepository.save(new Role("EMPLOYEE", "Employee role with access to employee-related features"));
            }
        };
    }
}
