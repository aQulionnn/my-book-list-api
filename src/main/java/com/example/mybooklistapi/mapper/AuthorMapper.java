package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.author.AuthorResponse;
import com.example.mybooklistapi.contract.author.CreateAuthorRequest;
import com.example.mybooklistapi.contract.author.UpdateAuthorRequest;
import com.example.mybooklistapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(CreateAuthorRequest request);
    Author toEntity(UpdateAuthorRequest request);

    @Mapping(target = "fullName", source = "author")
    AuthorResponse toResponse(Author author);

    default String mapFullName(Author author) {
        return author.getFirstName() + " " + author.getLastName();
    }
}
