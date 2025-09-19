package com.gohul.CustomerService.dto;

import com.gohul.CustomerService.constant.SyncStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSyncUpdateResponseDto {

    @NotBlank(message = "Customer id is required")
    private Long id;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be followed the correct format")
    private String email;
    @NotNull(message = "syncStatus is required")
    private SyncStatus syncStatus;

}
