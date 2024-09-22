package com.pdp.yourmeal.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.yourmeal.dto.response.ErrorMessageDTO;
import jakarta.servlet.ServletOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Aliabbos Ashurov
 * @since 22/September/2024  10:34
 **/
@Configuration
@RequiredArgsConstructor
public class CustomHandlers {

    private final ObjectMapper objectMapper;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            accessDeniedException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = accessDeniedException.getMessage();
            ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.of("403", errorMessage, errorPath);
            response.setStatus(404);
            ServletOutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, errorMessageDTO);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            authException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = authException.getMessage();
            ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.of("401", errorMessage, errorPath);
            response.setStatus(401);
            ServletOutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, errorMessageDTO);
        };
    }
}
