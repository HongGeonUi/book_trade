package com.example.book_trade.infrastructure.auth;

import com.example.book_trade.application.auth.CustomUserDetails;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "role_";

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateAccessToken(Authentication authentication) {
        return generateJwtTokenWithDay(authentication, 1);
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateJwtTokenWithDay(authentication, 30);
    }

    public String generateJwtTokenWithDay(Authentication authentication, int day) throws JwtException {
        Date now = new Date();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("book_trade")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(day).toMillis()))
                .setSubject(principal.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }




}

