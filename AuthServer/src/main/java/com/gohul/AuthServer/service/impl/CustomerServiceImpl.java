package com.gohul.AuthServer.service.impl;

import com.gohul.AuthServer.constant.SyncStatus;
import com.gohul.AuthServer.dto.CustomerDto;
import com.gohul.AuthServer.exception.ResourceAlreadyExistException;
import com.gohul.AuthServer.exception.ResourceNotFoundException;
import com.gohul.AuthServer.kafka.CustomerEventProducer;
import com.gohul.AuthServer.model.Authority;
import com.gohul.AuthServer.model.Customer;
import com.gohul.AuthServer.repo.AuthorityRepo;
import com.gohul.AuthServer.repo.CustomerRepo;
import com.gohul.AuthServer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder encoder;
    private final CustomerEventProducer producer;

    @Value("${custom-authority.default-customer-role}")
    private String defaultAuthority;

    @Override
    public void createCustomer(CustomerDto dto) {

        Optional<Customer> old = customerRepo.getByEmail(dto.getEmail());
        if(old.isPresent()) throw new ResourceAlreadyExistException("Customer", "Email", dto.getEmail());
        Authority authority = authorityRepo.findDistinctByName(defaultAuthority);
        Customer customer = Customer.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .syncStatus(SyncStatus.SYNCING)
                .authorities(Set.of(authority))
                .build();
        customerRepo.save(customer);
        dto.setPassword(null);
        producer.sendMessageToCreateCustomer(dto);
    }


    @Override
    public void resetCustomerPassword(CustomerDto dto) {
       Customer customer = customerRepo.getByEmail(dto.getEmail())
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "Email", dto.getEmail()));
       customer.setPassword(encoder.encode(dto.getPassword()));
       customerRepo.save(customer);
    }

    @Override
    public void deleteCustomerByEmail(String email) {
       Customer customer = customerRepo.getByEmail(email)
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "Email", email));
       customerRepo.deleteByEmail(email);
       producer.sendMessageToDeleteCustomer(CustomerDto.builder()
                        .id(customer.getId())
                        .email(customer.getEmail())
                        .build());
    }

    @Override
    public void updateCustomerSyncStatus(CustomerDto dto) {
        Customer syncedCustomer = customerRepo.getByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Email", dto.getEmail()));
        syncedCustomer.setSyncStatus(dto.getSyncStatus());
        customerRepo.save(syncedCustomer);
    }
}
