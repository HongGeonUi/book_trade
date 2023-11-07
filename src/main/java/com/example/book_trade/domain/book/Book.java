package com.example.book_trade.domain.book;

import com.example.book_trade.domain.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String title;
    private int price;
    private String author;
    private String image;


    @Builder
    public Book(Long id, String title, int price, String author, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.image = image;
    }
}
