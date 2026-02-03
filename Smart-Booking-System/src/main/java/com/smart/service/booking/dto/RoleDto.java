package com.smart.service.booking.dto;

import com.smart.service.booking.enums.RoleType;

import lombok.Data;

@Data
public class RoleDto {
    private RoleType roleType;   // ✅ enum
}
