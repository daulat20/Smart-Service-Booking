package com.smart.service.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.RoleDto;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.enums.RoleType;
import com.smart.service.booking.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /** Create or Save Role — prevents duplicate role creation */
    public Role saveRole(RoleDto roleDto) {
        RoleType roleType = roleDto.getRoleType();

        if (roleRepository.existsByRoleType(roleType)) {
            throw new RuntimeException("Role already exists: " + roleType);
        }

        Role role = new Role();
        role.setRoleType(roleType);
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
}
