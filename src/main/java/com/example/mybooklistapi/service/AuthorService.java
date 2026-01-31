package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.Author;
import com.example.mybooklistapi.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Async
    public CompletableFuture<Author> createAsync(Author author) {
        return CompletableFuture.supplyAsync(() -> authorRepository.save(author));
    }

    @Async
    public CompletableFuture<List<Author>> getAllAsync() {
        return CompletableFuture.supplyAsync(authorRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Author>> getByIdAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> authorRepository.findById(id));
    }

    @Async
    public CompletableFuture<Void> updateAsync(UUID id, Author updatedAuthor) {
        return CompletableFuture.runAsync(() -> {
            var author = authorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Author not found: " + id));

            author.setFirstName(updatedAuthor.getFirstName());
            author.setLastName(updatedAuthor.getLastName());

            authorRepository.save(author);
        });
    }

    @Async
    public CompletableFuture<Boolean> deleteAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (!authorRepository.existsById(id))
                return false;

            authorRepository.deleteById(id);
            return true;
        });
    }
}
