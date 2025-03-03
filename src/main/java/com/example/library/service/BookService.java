package com.example.library.service;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.dto.BookUpdateRequestDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<BookResponseDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookResponseDto update(Long bookId, BookUpdateRequestDto dto) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        // 제목 수정
        if (dto.getTitle() != null) {
            book.setTitle(dto.getTitle());
        }

        // 저자 수정
        if (dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
            List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
            book.getBookAuthors().clear();
            authors.forEach(book::addAuthor);
        }

        return new BookResponseDto(book);
    }

}
