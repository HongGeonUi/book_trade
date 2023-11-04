package com.example.book_trade.domain.auth.repository;

import java.util.Optional;

public interface TokenRepository {
    void setValue(Long key, String value);

    Optional<String> getValue(Long key);

    void deleteValue(Long key);

    boolean isExists(Long key);
}
