package com.example.book_trade.application.member;

import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.RoleType;
import com.example.book_trade.domain.member.Sex;
import com.example.book_trade.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void signup(String email, String password, String name, Sex sex) {
        isValidationEmail(email);
        memberRepository.save(Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .sex(sex)
                .role(RoleType.COMMON)
                .build());

    }

    @Transactional
    public void updateMember(String email, String password, String name, Sex sex) {
        Member member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        member.update(password, name, sex);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void isValidationEmail(String email) {
        if(memberRepository.existsByEmail(email)) throw new RuntimeException("이미 존재하는 이메일입니다.");
    }


}
