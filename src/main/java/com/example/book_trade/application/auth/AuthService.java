package com.example.book_trade.application.auth;

import com.example.book_trade.domain.auth.repository.TokenRepository;
import com.example.book_trade.domain.member.Member;
import com.example.book_trade.domain.member.PasswordManager;
import com.example.book_trade.domain.member.repository.MemberRepository;
import com.example.book_trade.infrastructure.auth.JwtAuthenticationManager;
import com.example.book_trade.infrastructure.auth.JwtTokenProvider;
import com.example.book_trade.presentation.auth.dto.*;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static int EXPIRATION_PERIOD = 7;

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final JwtAuthenticationManager authenticationManager;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordManager passwordManager;

    private final JwtTokenProvider tokenProvider;

    @Transactional
    public LogInResponse logIn(LogInRequest req) {
        String email = req.email();
        String password = req.password();

        Member member = validateMember(email, password);
        CustomUserDetails userDetails = CustomUserDetails.fromEntity(member);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, req.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);

        String accessToken = tokenProvider.generateAccessToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        tokenRepository.setValue(member.getId(), refreshToken);

        return new LogInResponse(accessToken, refreshToken);
    }

    @Transactional
    public void logOut(LogOutRequest req) {
        Long id = authenticationManager
                .getIdByToken(req.refreshToken())
                .orElseThrow(() -> new JwtException("유효하지 않은 토큰입니다."));

        tokenRepository.deleteValue(id);
    }

    // HttpServletRequest 로 변경 가능
    @Transactional
    public RefreshAuthenticationResponse refreshAuthentication(RefreshAuthenticationRequest req) {
        String accessToken = req.accessToken();
        String refreshToken = req.refreshToken();

        Long id = validateTokens(accessToken, refreshToken);

        Authentication authentication = authenticationManager.getAuthentication(refreshToken);

        if (!validateRefreshToken(id, refreshToken)) {
            refreshToken = tokenProvider.generateRefreshToken(authentication);
            tokenRepository.setValue(id, refreshToken);
        }

        String newAccessToken = tokenProvider.generateAccessToken(authentication);

        return new RefreshAuthenticationResponse(newAccessToken, refreshToken);
    }

    // Exception 처리 필요
    private Member validateMember(String email, String password) {
        return memberRepository
                .findByEmail(email)
                .filter(u -> passwordManager.checkPassword(password, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("인증 실패입니다."));
    }

    private Long validateTokens(String accessToken, String refreshToken) {
        Long IdByAccessToken = authenticationManager
                .getIdByToken(accessToken)
                .orElseThrow(() -> new JwtException("유효하지 않은 토큰입니다."));
        Long IdByRefreshToken = authenticationManager
                .getIdByToken(refreshToken)
                .orElseThrow(() -> new JwtException("유효하지 않은 토큰입니다."));

        if (!IdByAccessToken.equals(IdByRefreshToken)) {
            throw new JwtException("유효하지 않은 토큰입니다.");
        }

        return IdByAccessToken;
    }

    private boolean validateRefreshToken(Long id, String refreshToken) {
        String refreshTokenSaved = tokenRepository.getValue(id).orElse(null);

        return refreshToken.equals(refreshTokenSaved);
    }
}
