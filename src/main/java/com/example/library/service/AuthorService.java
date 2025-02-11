package com.example.library.service;

import com.example.library.dto.AuthorRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponseDto save(AuthorRequestDto dto) {
        Author author = new Author(dto.getName());
        authorRepository.save(author);
        return new AuthorResponseDto(author.getId(), author.getName());
    }
}
