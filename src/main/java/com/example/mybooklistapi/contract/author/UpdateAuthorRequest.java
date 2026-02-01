package com.example.mybooklistapi.contract.author;

public record UpdateAuthorRequest(
        String firstName,
        String lastName
) {
}
