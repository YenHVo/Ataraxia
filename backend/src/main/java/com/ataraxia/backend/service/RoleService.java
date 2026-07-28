package com.ataraxia.backend.service;

import com.ataraxia.backend.entity.Role;
import com.ataraxia.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

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

    public Role saveRole(Role role) {

        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new RuntimeException("Role already exists");
        }
        
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
