package com.smart.service.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.service.booking.entity.Role;
import com.smart.service.booking.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Fixed: parameter type is RoleType enum, not String
    Optional<Role> findByRoleType(RoleType roleType);

    boolean existsByRoleType(RoleType roleType);
}
