package com.example.mybooklistapi.repository;

import com.example.mybooklistapi.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, UUID> {
    @Query("""
        select count(ba) > 0
        from BookAuthor ba
        where ba.book.id = :bookId and ba.author.id = :authorId
    """)
    boolean linkExists(UUID bookId, UUID authorId);
}
