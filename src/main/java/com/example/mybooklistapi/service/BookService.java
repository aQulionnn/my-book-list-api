package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.Book;
import com.example.mybooklistapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public CompletableFuture<Page<Book>> getAllAsync(Pageable pageable) {
        return CompletableFuture.completedFuture(bookRepository.findAll(pageable));
    }

    @Cacheable(value = "books", key = "#id", unless = "#result == null")
    public Book getById(UUID id) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return bookRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "books", key = "#id")
    public void update(UUID id, Book updatedBook) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        book.setTitle(updatedBook.getTitle());
        bookRepository.save(book);
    }

    @CacheEvict(value = "books", key = "#id")
    public boolean delete(UUID id) {
        if (!bookRepository.existsById(id))
            return false;

        bookRepository.deleteById(id);
        return true;
    }
}