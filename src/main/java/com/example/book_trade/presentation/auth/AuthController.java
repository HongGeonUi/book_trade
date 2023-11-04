package com.example.book_trade.presentation.auth;

import com.example.book_trade.application.auth.AuthService;
import com.example.book_trade.presentation.auth.dto.LogInRequest;
import com.example.book_trade.presentation.auth.dto.LogOutRequest;
import com.example.book_trade.presentation.auth.dto.RefreshAuthenticationRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인 api", description = "아이디와 비밀번호를 통해 로그인한다.")
    @PostMapping("api/auth/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LogInRequest req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.logIn(req));
    }

    @Operation(summary = "로그아웃 api", description = "토큰을 이용해 로그아웃, 레디스에서 refreshtoken 제거")
    @DeleteMapping("api/auth/logout")
    public ResponseEntity<?> logOut(@Valid @RequestBody LogOutRequest req) {
        authService.logOut(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "재인증 api", description = "access Token과 refresh Token 재발급")
    @PostMapping("api/auth/refresh")
    public ResponseEntity<?> refreshAuthentication(@Valid @RequestBody RefreshAuthenticationRequest req) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.refreshAuthentication(req));
    }

}
