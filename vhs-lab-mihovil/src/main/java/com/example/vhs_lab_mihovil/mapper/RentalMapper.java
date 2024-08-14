package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.model.Rental;
import com.example.vhs_lab_mihovil.model.User;
import com.example.vhs_lab_mihovil.model.Vhs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalMapper extends GenericMapper<Rental, RentalDto> {
    RentalMapper MAPPER = Mappers.getMapper(RentalMapper.class);

    @Mapping(source = "user", target = "userId", qualifiedByName = "getUserId")
    @Mapping(source = "vhs", target = "vhsId", qualifiedByName = "getVhsId")
    RentalDto toDto(Rental rental);

    @Named("getUserId")
    static Integer getUserId(User user) {
        return user.getId();
    }

    @Named("getVhsId")
    static Integer getVhsId(Vhs vhs) {
        return vhs.getId();
    }
}
