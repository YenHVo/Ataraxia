package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.Role;
import com.ataraxia.backend.repository.RoleRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleService {
    
    public final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Role not found with id: " + id
        ));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByNameContainingIgnoreCase(name).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Role not found with name: " + name
        ));
    }

    public Role createRole(Role role) {
        if (roleRepository.findByNameContainingIgnoreCase(role.getName()).isPresent()) {
            throw new RuntimeException("Role already exists");
        }
        
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
