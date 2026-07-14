package com.employee.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey123";

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + 86400000))
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()),
                        Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
