package com.example.studyapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private final String secretKey;
    private final Long expirationTimeInMillis;
    private final SecretKey key;


    public JwtUtils(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") Long expirationTimeInMillis) {
        this.secretKey = secretKey;
        this.expirationTimeInMillis = expirationTimeInMillis;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String user(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> role(String token) {
        return extractAllClaims(token)
                .get("role", List.class);
    }


    public boolean isTokenValid(String token){
        try {
            extractAllClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
