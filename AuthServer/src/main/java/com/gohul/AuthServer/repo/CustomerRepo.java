package com.gohul.AuthServer.repo;

import com.gohul.AuthServer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

    Optional<Customer> getByEmail(String email);

    void deleteByEmail(String email);
}
