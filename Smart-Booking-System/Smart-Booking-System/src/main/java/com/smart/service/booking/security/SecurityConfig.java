package com.smart.service.booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers("/actuator/**").hasRole("ADMIN")

                // 🔐 ADMIN only (URL-level guard; also enforced via @PreAuthorize)
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/role/**").hasRole("ADMIN")

                // 🔐 PROVIDER or ADMIN
                .requestMatchers("/api/providers/**").hasAnyRole("PROVIDER", "ADMIN")
                .requestMatchers("/api/services/**").hasAnyRole("PROVIDER", "ADMIN")

                // 🔐 USER, PROVIDER, ADMIN (any authenticated)
                .requestMatchers("/api/bookings/**").hasAnyRole("USER", "PROVIDER", "ADMIN")
                .requestMatchers("/api/payments/**").hasAnyRole("USER", "PROVIDER", "ADMIN")
                .requestMatchers("/api/users/**").hasAnyRole("USER", "PROVIDER", "ADMIN")
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
