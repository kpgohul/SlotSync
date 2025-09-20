package com.gohul.CustomerService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequestDto {

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be followed the correct format.")
    private String email;

    @NotBlank(message = "Name is required.")
    @Size(min = 4, message = "Name should contain more than or equal to 4 characters.")
    private String name;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password should contain at least 8 characters.")
    private String password;

}
