package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.PriceDto;
import com.example.vhs_lab_mihovil.model.Price;
import org.mapstruct.Mapper;

@Mapper
public interface PriceMapper extends GenericMapper<Price, PriceDto> {
}
