package com.example.book_trade.presentation.book.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalBookResponseTest {

    @DisplayName("네이버 api JSON 변환 테스트")
    @Test
    public void testExternalBookResponseConstructor() throws JSONException {
        // Given
        JSONObject itemJson = new JSONObject();
        itemJson.put("title", "자바의 정석");
        itemJson.put("image", "sample.jpg");
        itemJson.put("author", "홍건의");
        itemJson.put("isbn", "12345");
        itemJson.put("discount", "12000");

        // When
        ExternalBookResponse externalBookResponse = new ExternalBookResponse(itemJson);

        // Then
        assertEquals("자바의 정석", externalBookResponse.getTitle());
        assertEquals("sample.jpg", externalBookResponse.getImage());
        assertEquals("홍건의", externalBookResponse.getAuthor());
        assertEquals(12345L, externalBookResponse.getIsbn());
        assertEquals(12000, externalBookResponse.getDiscount());
    }
}
