package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.book.*;
import com.example.mybooklistapi.mapper.BookMapper;
import com.example.mybooklistapi.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping("")
    ResponseEntity<BookResponse> create(@RequestBody CreateBookRequest request) {
        var future = bookService.createAsync(bookMapper.toEntity(request));
        var createdBook = future.join();
        var response = bookMapper.toResponse(createdBook);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    ResponseEntity<List<BookResponse>> getAll() {
        var future = bookService.getAllAsync();
        var books = future.join();
        var response = books
                .stream()
                .map(bookMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    ResponseEntity<BookResponse> getById(@PathVariable UUID id) {
        var future = bookService.getByIdAsync(id);
        var book = future.join();

        if (book.isEmpty())
            return ResponseEntity.notFound().build();

        var response = bookMapper.toResponse(book.get());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateBookRequest request) {
        try {
            bookService.updateAsync(id, bookMapper.toEntity(request)).join();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var future = bookService.deleteAsync(id);
        var isDeleted = future.join();

        if (!isDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
