package com.smart.service.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.RoleDto;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    // Create or Save Role
    public Role saveRole(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleType(roleDto.getRoleType());
        return roleRepository.save(role);
    }

    // Get Role by RoleType (USER / ADMIN / PROVIDER)
//    public Role getRoleByType(RoleType roleType) {
//        return roleRepository.findByRoleType(roleType)
//                .orElseThrow(() -> new RuntimeException("Role not found: " + roleType));
//    }

    // Get Role by ID
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    // Get All Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Delete Role
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
