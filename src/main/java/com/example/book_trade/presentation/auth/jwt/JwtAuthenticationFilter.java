package com.example.book_trade.presentation.auth.jwt;

import com.example.book_trade.infrastructure.auth.JwtAuthenticationManager;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    private final List<String> excludedPath = List.of("/api/auth/login", "/api/member", "/api/auth/refresh");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (excludedPath.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            generateTokenExceptionMessage(response, "인증을 위해 Jwt 토큰이 필요합니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring("Bearer ".length());

        try {
            Authentication authentication = jwtAuthenticationManager.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredJwtException e) {
            generateTokenExceptionMessage(response, "토큰 기한이 만료되었습니다.");
        } catch (JwtException | UsernameNotFoundException e) {
            generateTokenExceptionMessage(response, "잘못된 토큰 형식입니다.");
        }

        filterChain.doFilter(request, response);
    }

    private static void generateTokenExceptionMessage(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("{\"message\": \"" + message + "\" }");
    }
}
