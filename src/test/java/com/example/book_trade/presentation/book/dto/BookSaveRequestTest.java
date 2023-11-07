package com.example.book_trade.presentation.book.dto;

import com.example.book_trade.domain.book.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookSaveRequestTest {

    @DisplayName("toEntity 테스트")
    @Test
    public void toEntityTest() {
        //given
        BookSaveRequest bookSaveRequest = new BookSaveRequest(123456L, "자바의 정석", "홍건의", 12000, "sample.jpg");

        //when
        Book book = bookSaveRequest.toEntity();

        //then
        assertEquals(123456L,book.getId());
        assertEquals("자바의 정석", book.getTitle());
        assertEquals("홍건의", book.getAuthor());
        assertEquals(12000, book.getPrice());
        assertEquals("sample.jpg", book.getImage());
    }
}
