package com.gohul.TrainService.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gohul.TrainService.exception.CustomAuthenticationEntryPoint;
import com.gohul.TrainService.exception.CustomAccessDeniedHandler;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationConverter converter) throws Exception {
        http.authorizeHttpRequests(req -> req

                        .requestMatchers(HttpMethod.POST,  "/api/v1/train**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/train**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,  "/api/v1/train**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/v1/train**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST,  "/api/v1/route**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/route**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,  "/api/v1/route**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/v1/route**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST,  "/api/v1/station**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/station**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,  "/api/v1/station**").hasRole("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/v1/station**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST,  "/api/v1/schedule**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/schedule**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE,  "/api/v1/schedule**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/v1/schedule**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST,  "/api/v1/seat**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/seat**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE,  "/api/v1/seat**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/v1/seat**").hasAnyRole("SUPER_ADMIN", "ADMIN", "USER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)))
                .exceptionHandling(ehc ->
                        ehc.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                .accessDeniedHandler(new CustomAccessDeniedHandler()))
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();

    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // important
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }


//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> builder
//                .modules(new JavaTimeModule())
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    }
}
