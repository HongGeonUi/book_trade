package com.example.book_trade.infrastructure.book;

import com.example.book_trade.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(final Long id);

    boolean existsById(final Long id);

    void deleteById(final Long id);
}
