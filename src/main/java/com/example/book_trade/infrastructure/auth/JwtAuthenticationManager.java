package com.example.book_trade.infrastructure.auth;

import com.example.book_trade.application.auth.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtAuthenticationManager {
    private static final String AUTHORITIES_KEY = "role_";

    @Value("${jwt.secret}")
    private String secretKey;

    public Authentication getAuthentication(String token) {

        Claims claims = getBodyByToken(token);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        String id = claims.getId();

        CustomUserDetails principal = CustomUserDetails.builder()
                .id(id)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Optional<Long> getIdByToken(String token) {
        return Optional.of(Long.valueOf(getBodyByToken(token).getSubject()));
    }

    public long getExpirationDayByToken(String token) {
        long now = new Date(System.currentTimeMillis()).getTime();
        long expiration = getExpirationByToken(token).getTime();

        return TimeUnit.DAYS.convert((expiration - now), TimeUnit.MILLISECONDS);
    }

    public Date getExpirationByToken(String token) {
        return getBodyByToken(token).getExpiration();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims getBodyByToken(String token) throws JwtException {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

