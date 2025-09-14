package com.gohul.AuthServer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String rawPass = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if(encoder.matches(rawPass, userDetails.getPassword()))
            return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        else
            throw new BadCredentialsException("Email or Password is incorrect");
    }

    @Override
    public boolean supports(Class<?> authentication) {
       return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
