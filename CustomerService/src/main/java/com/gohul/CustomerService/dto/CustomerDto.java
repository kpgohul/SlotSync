package com.gohul.CustomerService.dto;

import com.gohul.CustomerService.constant.GenderType;
import jakarta.validation.constraints.*;

public class CustomerDto {

    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Gender is required")
    private GenderType gender;

    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private int age;
}
