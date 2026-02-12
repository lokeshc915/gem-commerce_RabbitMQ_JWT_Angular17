package com.gem.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class JwtUtil {
  private JwtUtil() {}

  public static Key signingKey(String secret) {
    byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
    if (bytes.length < 32) {
      byte[] padded = new byte[32];
      System.arraycopy(bytes, 0, padded, 0, bytes.length);
      for (int i = bytes.length; i < 32; i++) padded[i] = (byte) '0';
      bytes = padded;
    }
    return Keys.hmacShaKeyFor(bytes);
  }

  public static String generateToken(String secret, String subject, List<String> roles, long ttlSeconds) {
    Instant now = Instant.now();
    Map<String, Object> claims = Map.of("roles", roles);
    return Jwts.builder()
      .subject(subject)
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plusSeconds(ttlSeconds)))
      .claims(claims)
      .signWith(signingKey(secret))
      .compact();
  }

  public static Claims parse(String secret, String token) {
    return Jwts.parser()
      .verifyWith((javax.crypto.SecretKey) signingKey(secret))
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }
}
