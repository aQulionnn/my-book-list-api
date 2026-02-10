package com.example.mybooklistapi.mapper;

import com.example.mybooklistapi.contract.imprint.CreateImprintRequest;
import com.example.mybooklistapi.contract.imprint.ImprintResponse;
import com.example.mybooklistapi.contract.imprint.UpdateImprintRequest;
import com.example.mybooklistapi.model.Imprint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImprintMapper {
    Imprint toEntity(CreateImprintRequest request);
    Imprint toEntity(UpdateImprintRequest request);

    ImprintResponse toResponse(Imprint imprint);
}
