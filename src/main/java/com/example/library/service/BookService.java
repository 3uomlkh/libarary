package com.example.library.service;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookResponseDto save(BookRequestDto dto) {

        Book book = new Book(dto.getTitle());

        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        authors.forEach(book::addAuthor);

        bookRepository.save(book);

        return new BookResponseDto(book);
    }
}
