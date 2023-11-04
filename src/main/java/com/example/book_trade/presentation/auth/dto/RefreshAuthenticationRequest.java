package com.example.book_trade.presentation.auth.dto;

public record RefreshAuthenticationRequest(
        String accessToken,
        String refreshToken
) {
}
