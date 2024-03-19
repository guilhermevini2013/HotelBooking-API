package org.guilhermedev.hotelbooking.configurations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.guilhermedev.hotelbooking.models.user.User;
import org.guilhermedev.hotelbooking.repositories.UserRepository;
import org.guilhermedev.hotelbooking.services.token.JWTService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter
@Configuration
public class SecurityFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public SecurityFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String subjectCredential = jwtService.validateToken(token);
            User userFound = userRepository.findByCredential(subjectCredential).orElseThrow(() -> new EntityNotFoundException("Credential not found"));
            var authenticated = UsernamePasswordAuthenticationToken.authenticated(userFound, null, userFound.getRoles());
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authentication");
        if (token == null) return null;
        return token.replace("Bearer ", "");
    }
}
