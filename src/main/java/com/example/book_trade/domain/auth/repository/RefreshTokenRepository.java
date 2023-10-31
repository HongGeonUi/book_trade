package com.example.book_trade.domain.auth.repository;

public interface RefreshTokenRepository {
    public void setValue(Long key, String value);

    public String getValue(Long key);

    public void deleteValue(Long key);

    public boolean isExists(Long key);
}
