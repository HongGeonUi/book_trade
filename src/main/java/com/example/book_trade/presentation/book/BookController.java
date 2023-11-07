package com.example.book_trade.presentation.book;

import com.example.book_trade.application.book.BookService;
import com.example.book_trade.presentation.book.dto.BookSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class BookController {

    private final BookService bookService;

    @Operation(summary = "책 저장 api", description = "책 정보를 저장한다.")
    @PostMapping("api/book")
    public ResponseEntity<?> bookSave(BookSaveRequest req) {
        bookService.saveBook(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "책 삭제 api", description = "책 정보를 삭제한다.")
    @DeleteMapping("api/book/{id}")
    public ResponseEntity<?> bookDelete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status((HttpStatus.OK))
                .build();
    }

}
