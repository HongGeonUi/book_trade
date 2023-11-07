package com.example.book_trade.application.book;

import com.example.book_trade.presentation.book.dto.ExternalBookResponse;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NaverBookApiServiceTest {
    private String clientId;
    private String clientSecret;
    private NaverBookApiService naverBookApiService;

    @BeforeEach
    void setup() {
        clientId = "xvzGaIYoXEznQiCUoVLm";
        clientSecret = "XsjcvvHMqG";
        naverBookApiService = new NaverBookApiService(clientId,clientSecret);
    }

    @DisplayName("네이버 api 테스트")
    @Test
    public void searchTest() throws JSONException {
        //given
        String query = "java";

        //when
        List<ExternalBookResponse> bookResponseList = naverBookApiService.search(query);

        //then
        assertEquals(bookResponseList.size(),10);
    }
}
