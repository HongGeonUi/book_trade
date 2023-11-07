package com.example.book_trade.presentation.book.dto;


import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
@Getter
public class ExternalBookResponse {
    private final Long isbn;
    private final String title;
    private final String author;
    private final int discount;
    private final String image;

    public ExternalBookResponse(JSONObject itemjson) throws JSONException {
        this.title = itemjson.getString("title");
        this.image = itemjson.getString("image");
        this.author = itemjson.getString("author");
        this.isbn = Long.valueOf(itemjson.getString("isbn"));
        this.discount = Integer.parseInt(itemjson.getString("discount"));
    }
}
