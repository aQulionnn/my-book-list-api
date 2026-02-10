package com.example.mybooklistapi.repository;

import com.example.mybooklistapi.model.Imprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImprintRepository extends JpaRepository<Imprint, UUID> {
}
