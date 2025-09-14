package com.gohul.AuthServer.controller;

import com.gohul.AuthServer.dto.CustomerDto;
import com.gohul.AuthServer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerDto dto)
    {
        service.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody CustomerDto dto)
    {
        service.resetCustomerPassword(dto);
        return ResponseEntity.status(OK).body("Password changed successfully.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "email") String email)
    {
        service.deleteCustomerByEmail(email);
        return ResponseEntity.noContent().build();
    }


}
