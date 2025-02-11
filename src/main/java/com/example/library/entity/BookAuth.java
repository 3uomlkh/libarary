package com.example.library.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "book_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookAuth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public BookAuth(Book book, Author author) {
        this.book = book;
        this.author = author;
        this.book.getBookAuthors().add(this);
        this.author.getBookAuths().add(this);
    }
}
