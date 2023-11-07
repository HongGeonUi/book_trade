package com.example.book_trade.presentation.member.dto;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.RoleType;
import com.example.book_trade.domain.member.Sex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupRequestTest {

    @DisplayName("toEntity 테스트")
    @Test
    public void toEntityTest() {
        // Given
        SignupRequest signupRequest = new SignupRequest("keaam12@gmail.com", "aaaAAA111!!!", "홍건의", Sex.MAN);

        // When
        Member result = signupRequest.toEntity();

        // Then
        assertEquals("keaam12@gmail.com", result.getEmail());
        assertEquals("aaaAAA111!!!", result.getPassword());
        assertEquals("홍건의", result.getName());
        assertEquals(Sex.MAN, result.getSex());
        assertEquals(RoleType.COMMON, result.getRole());
    }
}
