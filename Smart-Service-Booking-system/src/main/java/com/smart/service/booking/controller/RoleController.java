package com.smart.service.booking.controller;

import com.smart.service.booking.dto.RoleDto;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.service.RoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @GetMapping("/getrole")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
