package com.example.book_trade.presentation.book.dto;

import com.example.book_trade.domain.book.Book;

public record BookSaveRequest(
        Long isbn,
        String title,
        String author,
        int discount,
        String image
) {
    public Book toEntity() {
        return Book.builder()
                .id(isbn)
                .title(title)
                .author(author)
                .price(discount)
                .image(image)
                .build();
    }
}
