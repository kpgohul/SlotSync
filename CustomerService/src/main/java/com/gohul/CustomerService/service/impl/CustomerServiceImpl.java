package com.gohul.CustomerService.service.impl;

import com.gohul.CustomerService.constant.SyncStatus;
import com.gohul.CustomerService.dto.CustomerCreateRequestDto;
import com.gohul.CustomerService.dto.CustomerDto;
import com.gohul.CustomerService.dto.CustomerSyncUpdateResponseDto;
import com.gohul.CustomerService.exception.ResourceAlreadyExistException;
import com.gohul.CustomerService.kafka.CustomerEventProducer;
import com.gohul.CustomerService.entity.Customer;
import com.gohul.CustomerService.repo.CustomerRepo;
import com.gohul.CustomerService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo repo;
    private final CustomerEventProducer producer;

    @Override
    public void createCustomer(CustomerCreateRequestDto dto) {
        Optional<Customer> past = repo.findByEmail(dto.getEmail());
        if(past.isPresent()) throw new ResourceAlreadyExistException("Customer", "Email", dto.getEmail());
        Customer newOne = Customer.builder()
                            .name(dto.getName())
                            .email(dto.getEmail())
                            .build();
        Customer customer = repo.save(newOne);
        producer.sendMessageConfirmCustomer(CustomerSyncUpdateResponseDto.builder()
                        .email(customer.getEmail())
                        .id(customer.getId())
                        .syncStatus(SyncStatus.SYNCED)
                .build());
    }

    @Override
    public void deleteCustomer(String email) {

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
