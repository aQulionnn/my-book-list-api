package com.example.mybooklistapi.contract.author;

public record CreateAuthorRequest(
        String firstName,
        String lastName
) {
}
