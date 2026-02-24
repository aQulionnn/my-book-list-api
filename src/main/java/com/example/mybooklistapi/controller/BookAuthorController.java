package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.book.author.BookAuthorResponse;
import com.example.mybooklistapi.contract.book.author.CreateBookAuthorRequest;
import com.example.mybooklistapi.mapper.BookAuthorMapper;
import com.example.mybooklistapi.model.BookAuthor;
import com.example.mybooklistapi.service.AuthorService;
import com.example.mybooklistapi.service.BookAuthorService;
import com.example.mybooklistapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-authors")
public class BookAuthorController {
    private final BookAuthorService bookAuthorService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookAuthorMapper bookAuthorMapper;

    public BookAuthorController(BookAuthorService bookAuthorService, BookService bookService, AuthorService authorService, BookAuthorMapper bookAuthorMapper) {
        this.bookAuthorService = bookAuthorService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookAuthorMapper = bookAuthorMapper;
    }

    @PostMapping("")
    ResponseEntity<BookAuthorResponse> create(@RequestBody CreateBookAuthorRequest request) {
        var book = bookService.getById(request.bookId());
        var futureAuthor = authorService.getByIdAsync(request.bookId());

        var author = futureAuthor.join();

        if (book == null || author.isEmpty())
            return ResponseEntity.notFound().build();

        var future = bookAuthorService.createAsync(new BookAuthor(author.get(), book));
        var createdBookAuthor = future.join();

        var response = bookAuthorMapper.toResponse(createdBookAuthor);

        return ResponseEntity.ok(response);
    }
}
