package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.Publisher;
import com.example.mybooklistapi.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Async
    public CompletableFuture<Publisher> createAsync(Publisher publisher) {
        return CompletableFuture.supplyAsync(() -> publisherRepository.save(publisher));
    }

    @Async
    public CompletableFuture<List<Publisher>> getAllAsync() {
        return CompletableFuture.supplyAsync(publisherRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Publisher>> getByIdAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> publisherRepository.findById(id));
    }

    @Async
    public CompletableFuture<Void> updateAsync(UUID id, Publisher updatedPublisher) {
        return CompletableFuture.runAsync(() -> {
            var publisher = publisherRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Publisher not found: " + id));

            publisher.setName(updatedPublisher.getName());
            publisherRepository.save(publisher);
        });
    }

    @Async
    public CompletableFuture<Boolean> deleteAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (!publisherRepository.existsById(id))
                return false;

            publisherRepository.deleteById(id);
            return true;
        });
    }
}