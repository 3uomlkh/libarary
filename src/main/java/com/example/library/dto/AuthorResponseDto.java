package com.example.library.dto;

import lombok.Getter;

@Getter
public class AuthorResponseDto {
    private final Long id;
    private final String name;

    public AuthorResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
