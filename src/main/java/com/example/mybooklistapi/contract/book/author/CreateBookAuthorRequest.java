package com.example.mybooklistapi.contract.book.author;

import java.util.UUID;

public record CreateBookAuthorRequest(
        UUID bookId,
        UUID authorId
) {
}
