package com.example.library.dto;

import com.example.library.entity.Book;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookResponseDto {
    private final Long id;
    private final String title;
    private List<Long> authorIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BookResponseDto(Long id, String title, List<Long> authorIds, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.authorIds = authorIds;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.authorIds = book.getBookAuthors().stream()
                .map(bookAuth -> bookAuth.getAuthor().getId())
                .distinct()
                .collect(Collectors.toList());
        this.createdAt = book.getCreatedAt();
        this.modifiedAt = book.getModifiedAt();
    }
}
