package com.example.book_trade.presentation.member.dto;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.RoleType;
import com.example.book_trade.domain.member.Sex;

public record SignupRequest(
        String email,
        String password,
        String name,
        Sex sex
) {
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .sex(sex)
                .role(RoleType.COMMON)
                .build();
    }
}
