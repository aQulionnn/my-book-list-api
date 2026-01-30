package com.example.mybooklistapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }
}