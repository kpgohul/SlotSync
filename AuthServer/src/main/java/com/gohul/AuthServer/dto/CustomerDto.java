package com.gohul.AuthServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gohul.AuthServer.constant.GenderType;
import com.gohul.AuthServer.constant.SyncStatus;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {


    private Long id;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be followed the correct format.")
    private String email;
    @Min(value = 4, message = "Name should contain more than or equal to 4 characters.")
    private String name;
    @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be Male, Female, or Other.")
    private GenderType gender;
    @Min(value = 18, message = "Customer should has more than 18+.")
    private int age;
    @JsonIgnore
    @NotBlank(message = "Password is required.")
    @Max(value = 8, message = "Password should contains more than 8 characters.")
    private String password;
    @JsonIgnore
    @Pattern(regexp = "^(SYNC_ERROR|SYNCED)$", message = "Sync status has unexpected value.")
    private SyncStatus syncStatus;

}
