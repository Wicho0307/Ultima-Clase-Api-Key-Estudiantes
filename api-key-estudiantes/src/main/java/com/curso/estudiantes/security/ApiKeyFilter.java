package com.curso.estudiantes.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private static final String API_KEY_HEADER = "Authorization";
    private final String apiKey;
    
    public ApiKeyFilter(@Value("${app.security.api-key}") String apiKey) {
        this.apiKey = apiKey;
    }

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {
        String requestApiKey = request.getHeader(API_KEY_HEADER);
        if (apiKey.equals(requestApiKey)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized\"}");
        }
    }
}