package com.example.mybooklistapi.contract.author;

import java.util.UUID;

public record AuthorResponse(
        UUID id,
        String fullName
) {
}
