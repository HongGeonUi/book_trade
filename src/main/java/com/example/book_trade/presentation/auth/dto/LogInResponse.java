package com.example.book_trade.presentation.auth.dto;

public record LogInResponse(
        String accessToken,
        String refreshToken
) {
}
