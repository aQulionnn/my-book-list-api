package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.BookAuthor;
import com.example.mybooklistapi.repository.BookAuthorRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Async
    public CompletableFuture<BookAuthor> createAsync(BookAuthor bookAuthor) {
        var exists = bookAuthorRepository.linkExists(
                bookAuthor.getBook().getId(),
                bookAuthor.getAuthor().getId()
        );

        if (exists)
            throw new IllegalStateException("Link already exists");

        return CompletableFuture.completedFuture(bookAuthorRepository.save(bookAuthor));
    }
}
