package com.gohul.AuthServer.service;

import com.gohul.AuthServer.dto.CustomerDto;

public interface CustomerService {

    void createCustomer(CustomerDto dto);

    void resetCustomerPassword(CustomerDto dto);

    boolean deleteCustomerByEmail(String email);

    void updateCustomerSyncStatus(CustomerDto dto);

}
