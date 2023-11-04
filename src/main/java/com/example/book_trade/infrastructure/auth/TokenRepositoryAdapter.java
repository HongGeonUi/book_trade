package com.example.book_trade.infrastructure.auth;

import com.example.book_trade.domain.auth.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepository {

    private final TokenRedisRepository refreshTokenRedisRepository;
    @Override
    public void setValue(Long key, String value) {
        refreshTokenRedisRepository.setValues(key,value);
    }

    @Override
    public Optional<String> getValue(Long key) {
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
