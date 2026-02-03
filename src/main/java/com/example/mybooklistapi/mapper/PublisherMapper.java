package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.publisher.CreatePublisherRequest;
import com.example.mybooklistapi.contract.publisher.PublisherResponse;
import com.example.mybooklistapi.contract.publisher.UpdatePublisherRequest;
import com.example.mybooklistapi.model.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher toEntity(CreatePublisherRequest request);
    Publisher toEntity(UpdatePublisherRequest request);

    PublisherResponse toResponse(Publisher publisher);
}
