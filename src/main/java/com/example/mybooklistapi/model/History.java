package com.example.mybooklistapi.model;

import com.example.mybooklistapi.enums.BookFormat;
import jakarta.persistence.*;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public int getReadingOrder() {
        return readingOrder;
    }

    public void setReadingOrder(int readingOrder) {
        this.readingOrder = readingOrder;
    }

    public int getReadingYear() {
        return readingYear;
    }

    public void setReadingYear(int readingYear) {
        this.readingYear = readingYear;
    }

    public Integer getReadingSeason() {
        return readingSeason;
    }

    public void setReadingSeason(Integer readingSeason) {
        this.readingSeason = readingSeason;
    }

    public boolean isReRead() {
        return reRead;
    }

    public void setReRead(boolean reRead) {
        this.reRead = reRead;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Imprint getImprint() {
        return imprint;
    }

    public void setImprint(Imprint imprint) {
        this.imprint = imprint;
    }
}