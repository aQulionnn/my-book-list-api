package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.publisher.*;
import com.example.mybooklistapi.mapper.PublisherMapper;
import com.example.mybooklistapi.service.PublisherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final PublisherMapper publisherMapper;

    public PublisherController(PublisherService publisherService, PublisherMapper publisherMapper) {
        this.publisherService = publisherService;
        this.publisherMapper = publisherMapper;
    }

    @PostMapping("")
    ResponseEntity<PublisherResponse> create(@RequestBody CreatePublisherRequest request) {
        var future = publisherService.createAsync(publisherMapper.toEntity(request));
        var createdPublisher = future.join();
        var response = publisherMapper.toResponse(createdPublisher);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    ResponseEntity<List<PublisherResponse>> getAll() {
        var future = publisherService.getAllAsync();
        var publishers = future.join();
        var response = publishers
                .stream()
                .map(publisherMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    ResponseEntity<PublisherResponse> getById(@PathVariable UUID id) {
        var future = publisherService.getByIdAsync(id);
        var publisher = future.join();

        if (publisher.isEmpty())
            return ResponseEntity.notFound().build();

        var response = publisherMapper.toResponse(publisher.get());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdatePublisherRequest request) {
        try {
            publisherService.updateAsync(id, publisherMapper.toEntity(request)).join();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var future = publisherService.deleteAsync(id);
        var isDeleted = future.join();

        if (!isDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}