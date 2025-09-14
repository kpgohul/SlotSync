package com.gohul.CustomerService.service.impl;

import com.gohul.CustomerService.dto.CustomerDto;
import com.gohul.CustomerService.exception.ResourceAlreadyExistException;
import com.gohul.CustomerService.model.Customer;
import com.gohul.CustomerService.repo.CustomerRepo;
import com.gohul.CustomerService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo repo;

    @Override
    public void createCustomer(CustomerDto dto) {
        Optional<Customer> past = repo.findByEmail(dto.getEmail());
        if(past.isEmpty()) throw new ResourceAlreadyExistException("Customer", "Email", dto.getEmail());
        Customer newOne = Customer.builder()
                            .name(dto.getName())
                            .email(dto.getEmail())
                            .age(dto.getAge())
                            .gender(dto.getGender())
                            .build();
        repo.save(newOne);
    }

    @Override
    public void deleteCustomer(String email) {
        Optional<Customer> past = repo.findByEmail(email);
        if(past.isEmpty()) throw new ResourceAlreadyExistException("Customer", "Email", email);
        repo.deleteByEmail(email);
    }

    @Override
    public CustomerDto fetchCustomerById(int id) {
        return null;
    }

    @Override
    public CustomerDto fetchCustomerByEmail(String email) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerDto dto) {

    }

    @Override
    public void fetchAllCustomers() {

    }
}
