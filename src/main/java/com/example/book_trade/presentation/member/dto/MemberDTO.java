package com.example.book_trade.presentation.member.dto;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDTO {
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "비밀번호는 최소 8자 이상이어야 하며, 숫자, 소문자, 대문자, 특수문자를 모두 포함해야 합니다.")
    private String password;
    @NotBlank
    private String name;
    private Sex sex;

    @Builder
    public MemberDTO(String email, String password, String name, Sex sex) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }
    public static MemberDTO fromEntity(Member member) {
        return MemberDTO.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .sex(member.getSex())
                .build();
    }
}
