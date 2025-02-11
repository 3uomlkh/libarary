package com.example.library.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookUpdateRequestDto {
    private String title;
    private List<Long> authorIds;
}
