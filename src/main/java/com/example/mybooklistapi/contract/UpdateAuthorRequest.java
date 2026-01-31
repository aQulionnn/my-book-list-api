package com.example.mybooklistapi.contract;

public record UpdateAuthorRequest(
        String firstName,
        String lastName
) {
}
