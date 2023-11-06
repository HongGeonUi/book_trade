package com.example.book_trade.domain.member;

public interface PasswordManager {
    String encryptPassword(String password);

    boolean checkPassword(String password, String hashedPassword);
}
