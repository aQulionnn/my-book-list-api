package com.example.mybooklistapi.controller;

import com.example.mybooklistapi.contract.imprint.CreateImprintRequest;
import com.example.mybooklistapi.contract.imprint.ImprintResponse;
import com.example.mybooklistapi.contract.imprint.UpdateImprintRequest;
import com.example.mybooklistapi.mapper.ImprintMapper;
import com.example.mybooklistapi.service.ImprintService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/imprints")
public class ImprintController {

    private final ImprintService imprintService;
    private final ImprintMapper imprintMapper;

    public ImprintController(ImprintService imprintService, ImprintMapper imprintMapper) {
        this.imprintService = imprintService;
        this.imprintMapper = imprintMapper;
    }

    @PostMapping("")
    ResponseEntity<ImprintResponse> create(@RequestBody CreateImprintRequest request) {
        var imprint = imprintService.createAsync(imprintMapper.toEntity(request)).join();
        return ResponseEntity.ok(imprintMapper.toResponse(imprint));
    }

    @GetMapping("")
    ResponseEntity<List<ImprintResponse>> getAll() {
        var imprints = imprintService.getAllAsync().join();
        var response = imprints.stream().map(imprintMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    ResponseEntity<ImprintResponse> getById(@PathVariable UUID id) {
        var imprint = imprintService.getByIdAsync(id).join();
        if (imprint.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(imprintMapper.toResponse(imprint.get()));
    }

    @PutMapping("{id}")
    ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateImprintRequest request) {
        try {
            imprintService.updateAsync(id, imprintMapper.toEntity(request)).join();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var isDeleted = imprintService.deleteAsync(id).join();
        if (!isDeleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
