package com.example.mybooklistapi.contract.book;

import java.util.UUID;

public record BookResponse (
        UUID id,
        String title
) {
}
