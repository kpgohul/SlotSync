package com.gohul.TrainService.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

        String expMsg = (authException != null && authException.getMessage() != null)
                ? authException.getMessage()
                : "Authentication Failed!";
        String path = request.getRequestURI();
        LocalDateTime dateTime = LocalDateTime.now();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("authorized-error", "Failed to Authenticate");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errBody = """
                {
                    "timestamp": "%s",
                    "status": %d,
                    "error": "%s",
                    "message": "%s",
                    "path": "%s"
                }
                """.formatted(dateTime, HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                expMsg, path);

        response.getWriter().write(errBody);
    }
}