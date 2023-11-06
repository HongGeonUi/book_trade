package com.example.book_trade.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void setValues(Long key, String data) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(String.valueOf(key), data);
        redisTemplate.expire(String.valueOf(key), 60L, TimeUnit.MINUTES);
    }

    public Optional<String> getValues(Long key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(String.valueOf(key)));
    }

    public void deleteValues(Long key) {
        redisTemplate.delete(String.valueOf(key));
    }

    public boolean isExists(Long key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(String.valueOf(key)));
    }


}
