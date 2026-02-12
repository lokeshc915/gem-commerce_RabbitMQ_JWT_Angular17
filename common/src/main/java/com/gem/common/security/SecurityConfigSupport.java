package com.gem.common.security;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public final class SecurityConfigSupport {
  private SecurityConfigSupport() {}

  public static SecurityFilterChain apiSecurity(HttpSecurity http, JwtAuthFilter jwtFilter) throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/actuator/**", "/h2-console/**").permitAll()
        .anyRequest().authenticated()
      )
      .headers(h -> h.frameOptions(f -> f.disable()))
      .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
      .httpBasic(Customizer.withDefaults())
      .build();
  }
}
