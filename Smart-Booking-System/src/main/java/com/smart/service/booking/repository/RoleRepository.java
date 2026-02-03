package com.smart.service.booking.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.service.booking.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(String roleType);

}

