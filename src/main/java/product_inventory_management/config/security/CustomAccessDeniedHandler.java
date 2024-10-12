package product_inventory_management.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import product_inventory_management.dto.exception.ExceptionResponseDTO;

import java.io.IOException;

@Configuration
public class CustomAccessDeniedHandler implements AccessDeniedHandler, AuthenticationEntryPoint {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO("Access denied. You do not have permission to perform this action.");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(exceptionResponse));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO("Access denied. You do not have permission to perform this action.");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(exceptionResponse));
    }
}
