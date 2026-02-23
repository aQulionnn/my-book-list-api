package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.book.author.BookAuthorResponse;
import com.example.mybooklistapi.model.BookAuthor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookAuthorMapper {
    BookAuthorResponse toResponse(BookAuthor bookAuthor);
}
