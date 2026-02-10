package com.example.mybooklistapi.service;

import com.example.mybooklistapi.model.Imprint;
import com.example.mybooklistapi.repository.ImprintRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ImprintService {

    private final ImprintRepository imprintRepository;

    public ImprintService(ImprintRepository imprintRepository) {
        this.imprintRepository = imprintRepository;
    }

    @Async
    public CompletableFuture<Imprint> createAsync(Imprint imprint) {
        return CompletableFuture.supplyAsync(() -> imprintRepository.save(imprint));
    }

    @Async
    public CompletableFuture<List<Imprint>> getAllAsync() {
        return CompletableFuture.supplyAsync(imprintRepository::findAll);
    }

    @Async
    public CompletableFuture<Optional<Imprint>> getByIdAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> imprintRepository.findById(id));
    }

    @Async
    public CompletableFuture<Void> updateAsync(UUID id, Imprint updatedImprint) {
        return CompletableFuture.runAsync(() -> {
            var imprint = imprintRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Imprint not found: " + id));

            imprint.setName(updatedImprint.getName());
            imprintRepository.save(imprint);
        });
    }

    @Async
    public CompletableFuture<Boolean> deleteAsync(UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            if (!imprintRepository.existsById(id))
                return false;

            imprintRepository.deleteById(id);
            return true;
        });
    }
}
