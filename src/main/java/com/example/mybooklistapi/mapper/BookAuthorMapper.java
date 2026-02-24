package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.book.author.BookAuthorResponse;
import com.example.mybooklistapi.model.BookAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookAuthorMapper {

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "authorId", source = "author.id")
    BookAuthorResponse toResponse(BookAuthor bookAuthor);
}
