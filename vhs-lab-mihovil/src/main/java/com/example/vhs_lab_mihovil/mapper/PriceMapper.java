package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.PriceDto;
import com.example.vhs_lab_mihovil.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper extends GenericMapper<Price, PriceDto> {
    PriceMapper MAPPER = Mappers.getMapper(PriceMapper.class);
}
