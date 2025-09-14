package com.gohul.AuthServer.repo;

import com.gohul.AuthServer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    Optional<Customer> getByEmail(String email);

    void deleteByEmail(String email);
}
