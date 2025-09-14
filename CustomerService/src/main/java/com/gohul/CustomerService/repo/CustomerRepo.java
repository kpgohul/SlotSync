package com.gohul.CustomerService.repo;

import com.gohul.CustomerService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    void deleteByEmail(String email);

}
