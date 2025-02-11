package com.example.library.controller;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.dto.BookUpdateRequestDto;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> save(@RequestBody BookRequestDto dto) {
        BookResponseDto bookResponseDto = bookService.save(dto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAll() {
        List<BookResponseDto> bookResponseDtos = bookService.findAll();
        return ResponseEntity.ok(bookResponseDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(
            @PathVariable Long id,
            @RequestBody BookUpdateRequestDto dto
    ) {
        BookResponseDto bookResponseDto = bookService.update(id, dto);
        return ResponseEntity.ok(bookResponseDto);
    }

}
