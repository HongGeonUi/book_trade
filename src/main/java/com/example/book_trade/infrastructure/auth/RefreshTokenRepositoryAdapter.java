package com.example.book_trade.infrastructure.auth;

import com.example.book_trade.domain.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    @Override
    public void setValue(Long key, String value) {
        refreshTokenRedisRepository.setValues(key,value);
    }

    @Override
    public String getValue(Long key) {
        return refreshTokenRedisRepository.getValues(key);
    }

    @Override
    public void deleteValue(Long key) {
        refreshTokenRedisRepository.deleteValues(key);
    }

    @Override
    public boolean isExists(Long key) {
        return refreshTokenRedisRepository.isExists(key);
    }
}
