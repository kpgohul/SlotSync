package com.gohul.CustomerService.service;

import com.gohul.CustomerService.dto.CustomerCreateRequestDto;
import com.gohul.CustomerService.dto.CustomerDto;
import com.gohul.CustomerService.dto.CustomerSyncUpdateResponseDto;

public interface CustomerService {

    void createCustomer(CustomerCreateRequestDto dto);
    void deleteCustomer(String email);
    CustomerDto fetchCustomerById(int id);
    CustomerDto fetchCustomerByEmail(String email);
    void updateCustomer(CustomerDto dto);
    void fetchAllCustomers();

}
