package com.example.mybooklistapi.model;

import com.example.mybooklistapi.enums.BookFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    private BookFormat format;

    private Integer pageCount;
    private int readingOrder;
    private int readingYear;
    private Integer readingSeason;
    private boolean reRead;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private Imprint imprint;

    public History() {
    }

    public History(
            BookFormat format,
            Integer pageCount,
            int readingOrder,
            int readingYear,
            Integer readingSeason,
            boolean reRead,
            Book book,
            Publisher publisher,
            Imprint imprint
    ) {
        this.format = format;
        this.pageCount = pageCount;
        this.readingOrder = readingOrder;
        this.readingYear = readingYear;
        this.readingSeason = readingSeason;
        this.reRead = reRead;
        this.book = book;
        this.publisher = publisher;
        this.imprint = imprint;
    }

}