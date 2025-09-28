package com.gohul.AuthServer.config;

import com.gohul.AuthServer.entity.Customer;
import com.gohul.AuthServer.entity.MyUserDetails;
import com.gohul.AuthServer.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepo.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s is not found!", email)));
        var authorities = customer.getAuthorities().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
        return new MyUserDetails(customer.getId(), customer.getEmail(), customer.getPassword(), authorities);
    }
}
