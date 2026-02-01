package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.book.*;
import com.example.mybooklistapi.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(CreateBookRequest request);
    Book toEntity(UpdateBookRequest request);

    BookResponse toResponse(Book book);
}
