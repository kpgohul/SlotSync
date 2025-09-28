package com.gohul.CustomerService.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class AuthorizationException implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        String errMsg = (accessDeniedException != null && accessDeniedException.getMessage() != null)
                ? accessDeniedException.getMessage()
                : "UnAuthorized";
        String path = request.getRequestURI();
        LocalDateTime dateTime = LocalDateTime.now();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("custom-authorized-error", "failed due to unauthorised");
        response.setContentType("application/json;charset=UTF-8");

        String errResBody = """
                {
                    "timestamp": "%s",
                    "status": %d,
                    "error": "%s",
                    "message": "%s",
                    "path": "%s"
                }
                """.formatted(dateTime,
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                errMsg,
                path);

        response.getWriter().write(errResBody);
    }
}
