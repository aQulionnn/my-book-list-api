package com.example.mybooklistapi.contract;

import java.util.UUID;

public record AuthorResponse(
        UUID id,
        String fullName
) {
}
