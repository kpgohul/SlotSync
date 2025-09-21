package com.gohul.AuthServer.config;

import com.gohul.AuthServer.entity.MyUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MyTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {

        var principal = context.getPrincipal();


        if(context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)){


            if(principal instanceof UsernamePasswordAuthenticationToken token) {
                Object userObj = token.getPrincipal();
                if (userObj instanceof MyUserDetails userDetails) {
                    context.getClaims().claim("customer_id", userDetails.getId());
                    context.getClaims().claim("customer_email", userDetails.getEmail());
                }
            }

            Set<String> roles = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
                    .stream()
                    .map(auth -> auth.replaceFirst("^ROLE_", ""))
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

            context.getClaims().claim("roles", roles);

        }
        else if("id_token".equals(context.getTokenType().getValue()))
        {
            if(principal instanceof UsernamePasswordAuthenticationToken token) {
                Object userObj = token.getPrincipal();
                if (userObj instanceof MyUserDetails userDetails) {
                    context.getClaims().claim("customer_id", userDetails.getId());
                    context.getClaims().claim("customer_email", userDetails.getEmail());
                }
            }
        }

    }
}
