package org.guilhermedev.hotelbooking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        configureRoutesEnterpriseSecurity(http);
        configureRoutesClientSecurity(http);
        configurePublicRoute(http);
        return http.cors(cors -> cors.setBuilder(http))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void configurePublicRoute(HttpSecurity httpSecurity) throws Exception {
        final String[] h2DataBaseRoutes = {
                "/h2",
                "/h2/**"
        };
        httpSecurity.authorizeHttpRequests(httpRequest -> httpRequest
                .requestMatchers(HttpMethod.POST, "user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "user/auth").permitAll()
                .requestMatchers(h2DataBaseRoutes).permitAll()
                .anyRequest().authenticated());
    }
    private  void configureRoutesClientSecurity(HttpSecurity http) throws Exception {
        final String[] clientRoutes = {
                "/commentary",
                "/hotel/**"
        };
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(clientRoutes).hasRole("CLIENT"));
    }

    private void configureRoutesEnterpriseSecurity(HttpSecurity http) throws Exception {
        final String[] enterpriseRoutes = {
                "/hotel",
                "/hotel/**"
        };
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(enterpriseRoutes).hasAnyRole("ENTERPRISE"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
