package com.gohul.CustomerService.service;

import com.gohul.CustomerService.dto.CustomerDto;

public interface CustomerService {

    void createCustomer(CustomerDto dto);
    void deleteCustomer(String email);
    CustomerDto fetchCustomerById(int id);
    CustomerDto fetchCustomerByEmail(String email);
    void updateCustomer(CustomerDto dto);
    void fetchAllCustomers();

}
