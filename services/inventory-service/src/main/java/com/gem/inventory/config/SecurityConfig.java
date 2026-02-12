package com.gem.inventory.config;

import com.gem.common.security.JwtAuthFilter;
import com.gem.common.security.SecurityConfigSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean JwtAuthFilter jwtAuthFilter(@Value("${gem.security.jwt-secret}") String secret){ return new JwtAuthFilter(secret); }
  @Bean SecurityFilterChain chain(HttpSecurity http, JwtAuthFilter f) throws Exception { return SecurityConfigSupport.apiSecurity(http, f); }
}
