package com.example.mybooklistapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue
    private UUID id;

    private int fromPage;
    private int toPage;
    private Integer readingYear;

    @ManyToOne
    private History history;

    public Progress() {
    }

    public Progress(int fromPage, int toPage, Integer readingYear, History history) {
        this.fromPage = fromPage;
        this.toPage = toPage;
        this.readingYear = readingYear;
        this.history = history;
    }

}