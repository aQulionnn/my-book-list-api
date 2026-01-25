package com.example.mybooklistapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
        name = "book_author",
        uniqueConstraints = @UniqueConstraint(columnNames = {"author_id", "book_id"})
)
public class BookAuthor {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Book book;

    public BookAuthor() {
    }

    public BookAuthor(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}