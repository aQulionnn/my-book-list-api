package com.example.mybooklistapi.contract.publisher;

import java.util.UUID;

public record PublisherResponse(
        UUID id,
        String name
) {
}
