package com.example.book_trade.infrastructure.book;

import com.example.book_trade.domain.book.Book;
import com.example.book_trade.domain.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryAdapterTest {
    private BookJpaRepository bookJpaRepository;
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookJpaRepository = Mockito.mock(BookJpaRepository.class);
        bookRepository = new BookRepositoryAdapter(bookJpaRepository);
    }

    @Test
    void saveTest() {
        //given
        Book book = Book.builder()
                .id(123456L)
                .title("자바의정석")
                .author("홍건의")
                .price(12000)
                .image("testImage")
                .build();
        Mockito.when(bookJpaRepository.save(book)).thenReturn(book);

        //when
        Book result = bookRepository.save(book);

        //then
        assertEquals(book,result);
    }

    @Test
    @DisplayName("findById test")
    void findByIdTest() {
        //given
        Long bookId = 123456L;
        Book bookExpected = Book.builder()
                .id(123456L)
                .title("자바의정석")
                .author("홍건의")
                .price(12000)
                .image("testImage")
                .build();
        Mockito.when(bookJpaRepository.findById(bookId)).thenReturn(Optional.of(bookExpected));

        //when
        Optional<Book> result = bookRepository.findById(bookId);

        //then
        assertTrue(result.isPresent());
        assertEquals(bookExpected, result.get());
    }

    @Test
    @DisplayName("existsById 테스트")
    void existsByIdTest() {
        //given
        Long bookId = 123456L;
        Mockito.when(bookJpaRepository.existsById(bookId)).thenReturn(true);

        //when
        boolean result = bookRepository.existsById(bookId);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("deleteById test")
    void deleteByIdTest() {
        //given
        Long bookId = 123456L;
        Mockito.doNothing().when(bookJpaRepository).deleteById(bookId);

        //when
        bookRepository.deleteById(bookId);
    }


}

