package com.gohul.AuthServer.dto;

import com.gohul.AuthServer.constant.SyncStatus;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
