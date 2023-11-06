package com.example.book_trade.presentation.auth.dto;

public record RefreshAuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
