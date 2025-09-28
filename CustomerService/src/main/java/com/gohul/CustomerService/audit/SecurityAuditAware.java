package com.gohul.CustomerService.audit;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("SecurityAuditAware")
public class SecurityAuditAware implements AuditorAware<Long> {

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            // Convert the claim to Integer
            Long customerId = jwt.getClaim("customer_id");
            return Optional.ofNullable(customerId);
        } else {
            // fallback if principal is not Jwt
            return Optional.empty(); // or throw exception if you expect JWT always
        }
    }
}
