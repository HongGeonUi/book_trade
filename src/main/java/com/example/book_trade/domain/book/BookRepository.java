package com.example.book_trade.domain.book;

import java.util.Optional;

public interface BookRepository {
    Book save(final Book book);

    Optional<Book> findById(final Long id);

    boolean existsById(final Long id);

    void deleteById(final Long id);
}
