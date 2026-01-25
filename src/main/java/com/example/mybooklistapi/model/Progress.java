package com.example.mybooklistapi.model;

import jakarta.persistence.*;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getFromPage() {
        return fromPage;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public int getToPage() {
        return toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public Integer getReadingYear() {
        return readingYear;
    }

    public void setReadingYear(Integer readingYear) {
        this.readingYear = readingYear;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}