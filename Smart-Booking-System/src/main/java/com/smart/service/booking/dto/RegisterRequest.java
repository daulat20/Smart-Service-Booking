package com.smart.service.booking.dto;


import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private Long roleId;


}
