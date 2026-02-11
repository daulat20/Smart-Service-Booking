package com.smart.service.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProviderRequestDTO {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 200, message = "Company name must be 2-200 characters")
    private String companyName;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "User ID is required")
    private Long userId;
}
