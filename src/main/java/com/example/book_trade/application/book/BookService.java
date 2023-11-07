package com.example.book_trade.application.book;

import com.example.book_trade.domain.book.BookRepository;
import com.example.book_trade.presentation.book.dto.BookSaveRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(BookSaveRequest req) {
        if(!bookRepository.existsById(req.isbn())) bookRepository.save(req.toEntity());
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
