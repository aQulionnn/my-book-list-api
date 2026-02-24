package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.book.*;
import com.example.mybooklistapi.event.BookCreatedEvent;
import com.example.mybooklistapi.mapper.BookMapper;
import com.example.mybooklistapi.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final ApplicationEventPublisher publisher;

    public BookController(BookService bookService, BookMapper bookMapper, ApplicationEventPublisher publisher) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.publisher = publisher;
    }

    @PostMapping("")
    ResponseEntity<BookResponse> create(@RequestBody CreateBookRequest request) {
        var future = bookService.createAsync(bookMapper.toEntity(request));
        var createdBook = future.join();

        publisher.publishEvent(new BookCreatedEvent(createdBook.getTitle()));

        var response = bookMapper.toResponse(createdBook);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    ResponseEntity<Page<BookResponse>> getAll(@ParameterObject Pageable pageable) {
        var future = bookService.getAllAsync(pageable);
        var books = future.join();
        var response = books.map(bookMapper::toResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    ResponseEntity<BookResponse> getById(@PathVariable UUID id) {
        var book = bookService.getById(id);

        if (book == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(bookMapper.toResponse(book));
    }

    @PutMapping("{id}")
    ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateBookRequest request) {
        try {
            bookService.update(id, bookMapper.toEntity(request));
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        if (!bookService.delete(id))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
