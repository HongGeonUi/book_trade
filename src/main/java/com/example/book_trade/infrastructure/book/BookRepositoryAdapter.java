package com.example.book_trade.infrastructure.book;

import com.example.book_trade.domain.book.Book;
import com.example.book_trade.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepository {

    private final BookJpaRepository bookJpaRepository;
    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookJpaRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return bookJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookJpaRepository.deleteById(id);
    }
}
