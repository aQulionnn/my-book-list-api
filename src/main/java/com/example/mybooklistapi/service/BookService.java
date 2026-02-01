package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.Book;
import com.example.mybooklistapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Async
    public CompletableFuture<Book> createAsync(Book book) {
        return CompletableFuture.supplyAsync(() -> bookRepository.save(book));
    }

    @Async
    public CompletableFuture<List<Book>> getAllAsync() {
        return CompletableFuture.supplyAsync(bookRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Book>> getByIdAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> bookRepository.findById(id));
    }

    @Async
    public CompletableFuture<Void> updateAsync(UUID id, Book updatedBook) {
        return CompletableFuture.runAsync(() -> {
            var book = bookRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

            book.setTitle(updatedBook.getTitle());
            bookRepository.save(book);
        });
    }

    @Async
    public CompletableFuture<Boolean> deleteAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (!bookRepository.existsById(id))
                return false;

            bookRepository.deleteById(id);
            return true;
        });
    }
}