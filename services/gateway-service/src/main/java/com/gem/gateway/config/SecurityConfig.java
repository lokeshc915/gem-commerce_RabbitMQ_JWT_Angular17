package com.gem.gateway.config;

import com.gem.common.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean JwtAuthFilter jwtAuthFilter(@Value("${gem.security.jwt-secret}") String secret){ return new JwtAuthFilter(secret); }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
    return http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/**", "/actuator/**", "/h2-console/**").permitAll()
        .anyRequest().authenticated()
      )
      .headers(h -> h.frameOptions(f -> f.disable()))
      .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
      .build();
  }
}
