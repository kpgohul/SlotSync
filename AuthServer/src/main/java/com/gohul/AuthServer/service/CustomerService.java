package com.gohul.AuthServer.service;

import com.gohul.AuthServer.dto.CustomerCreateRequestDto;
import com.gohul.AuthServer.dto.CustomerSyncUpdateRequestDto;

public interface CustomerService {

    void createCustomer(CustomerCreateRequestDto dto);

    void resetCustomerPassword(CustomerCreateRequestDto dto);

    void deleteCustomerByEmail(String email);

    void updateCustomerSyncStatus(CustomerSyncUpdateRequestDto dto);

}
