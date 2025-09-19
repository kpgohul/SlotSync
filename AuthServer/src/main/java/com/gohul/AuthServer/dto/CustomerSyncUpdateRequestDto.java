package com.gohul.AuthServer.dto;

import com.gohul.AuthServer.constant.SyncStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSyncUpdateRequestDto {

    @NotBlank(message = "Customer id is required")
    private Long id;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be followed the correct format")
    private String email;
    @NotNull(message = "syncStatus is required")
    private SyncStatus syncStatus;

}
