package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.AuthorResponse;
import com.example.mybooklistapi.contract.CreateAuthorRequest;
import com.example.mybooklistapi.contract.UpdateAuthorRequest;
import com.example.mybooklistapi.mapper.AuthorMapper;
import com.example.mybooklistapi.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("")
    ResponseEntity<AuthorResponse> create(@RequestBody CreateAuthorRequest request) {
        var future = authorService.createAsync(authorMapper.toEntity(request));
        var createdAuthor = future.join();
        var response = authorMapper.toResponse(createdAuthor);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    ResponseEntity<List<AuthorResponse>> getAll() {
        var future = authorService.getAllAsync();
        var authors = future.join();
        var response = authors
                .stream()
                .map(authorMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    ResponseEntity<AuthorResponse> getById(@PathVariable UUID id) {
        var future = authorService.getByIdAsync(id);
        var author = future.join();

        if (author.isEmpty())
            return ResponseEntity.notFound().build();

        var response = authorMapper.toResponse(author.get());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateAuthorRequest request) {
        try {
            authorService.updateAsync(id, authorMapper.toEntity(request)).join();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var future = authorService.deleteAsync(id);
        var isDeleted = future.join();

        if (!isDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
