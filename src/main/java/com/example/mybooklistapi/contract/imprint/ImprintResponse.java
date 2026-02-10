package com.example.mybooklistapi.contract.imprint;

import java.util.UUID;

public record ImprintResponse(
        UUID id,
        String name
) {
}
