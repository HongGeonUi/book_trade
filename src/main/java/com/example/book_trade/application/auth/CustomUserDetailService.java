package com.example.book_trade.application.auth;

import com.example.book_trade.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // ByUserName 이지만 PK 값인 id로 조회
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return CustomUserDetails.fromEntity(memberRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다.")));
    }
}
