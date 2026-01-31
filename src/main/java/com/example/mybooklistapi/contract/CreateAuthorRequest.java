package com.example.mybooklistapi.contract;

public record CreateAuthorRequest(
        String firstName,
        String lastName
) {
}
