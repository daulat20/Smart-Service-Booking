package com.smart.service.booking.controller;

import com.smart.service.booking.dto.RoleDto;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.service.RoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Role Controller — accessible ONLY by ADMIN role
 */
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // all role management is admin-only
public class RoleController {

    private final RoleService roleService;

    /** Create a new role — ADMIN only */
    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    /** Get all roles — ADMIN only */
    @GetMapping("/getrole")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    /** Get role by ID — ADMIN only */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    /** Delete a role — ADMIN only */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
