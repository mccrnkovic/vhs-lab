package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.VhsDto;
import com.example.vhs_lab_mihovil.model.Vhs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VhsMapper extends GenericMapper<Vhs, VhsDto> {
    VhsMapper MAPPER = Mappers.getMapper(VhsMapper.class);
}
