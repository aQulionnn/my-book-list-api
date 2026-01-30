package com.example.mybooklistapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
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

}