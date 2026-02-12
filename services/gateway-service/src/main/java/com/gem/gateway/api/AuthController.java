package com.gem.gateway.api;

import com.gem.common.security.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final String secret;
  private final long ttl;

  public AuthController(@Value("${gem.security.jwt-secret}") String secret,
                        @Value("${gem.security.jwt-ttl-seconds}") long ttl) {
    this.secret = secret; this.ttl = ttl;
  }

  public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
  public record LoginResponse(String token, String username, List<String> roles) {}

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    if ("admin".equals(req.username()) && "admin123".equals(req.password())) {
      List<String> roles = List.of("ADMIN","USER");
      return ResponseEntity.ok(new LoginResponse(JwtUtil.generateToken(secret, req.username(), roles, ttl), req.username(), roles));
    }
    if ("user".equals(req.username()) && "user123".equals(req.password())) {
      List<String> roles = List.of("USER");
      return ResponseEntity.ok(new LoginResponse(JwtUtil.generateToken(secret, req.username(), roles, ttl), req.username(), roles));
    }
    return ResponseEntity.status(401).build();
  }
}
