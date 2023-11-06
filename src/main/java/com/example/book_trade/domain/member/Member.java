package com.example.book_trade.domain.member;

import com.example.book_trade.domain.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Builder
    public Member(Long id, String email, String password, String name, Sex sex, RoleType role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.role = role;
    }

    public void update(String password, String name, Sex sex) {
        this.password = password;
        this.name = name;
        this.sex = sex;
    }

    public void setPasswordByEncryption(String passwordByEncryption) {
        this.password = passwordByEncryption;
    }
}
