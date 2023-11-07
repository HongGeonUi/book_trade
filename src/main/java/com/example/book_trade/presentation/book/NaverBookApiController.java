package com.example.book_trade.presentation.book;

import com.example.book_trade.application.book.NaverBookApiService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverBookApiController {

    private final NaverBookApiService naverBookApiService;

    @Operation(summary = "Naver Book Api", description = "네이버 api를 이용해 책 정보를 검색한다")
    @GetMapping("api/book/search/{query}")
    public ResponseEntity<?> bookSearch(@PathVariable String query) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(naverBookApiService.search(query));
    }
}
