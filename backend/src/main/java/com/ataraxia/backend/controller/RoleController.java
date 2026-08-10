package com.ataraxia.backend.controller;

import com.ataraxia.backend.entity.Role;
import com.ataraxia.backend.service.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {
    
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/name/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
