package com.example.mybooklistapi.contract.book.author;

import java.util.UUID;

public record BookAuthorResponse(
        UUID id,
        UUID bookId,
        UUID authorId
) {
}
