package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.RentalDto;
import com.example.vhs_lab_mihovil.model.Rental;
import com.example.vhs_lab_mihovil.model.User;
import com.example.vhs_lab_mihovil.model.Vhs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = VhsMapper.class)
public interface RentalMapper extends GenericMapper<Rental, RentalDto> {
    RentalMapper MAPPER = Mappers.getMapper(RentalMapper.class);

    @Mapping(source = "user", target = "userId", qualifiedByName = "getUserId")
    @Mapping(source = "vhs", target = "vhsId", qualifiedByName = "getVhsId")
    RentalDto toDto(Rental rental);

    @Mapping(source = "userId", target = "user", qualifiedByName = "getUser")
    @Mapping(source = "vhsId", target = "vhs", qualifiedByName = "getVhs")
    Rental toModel(RentalDto rentalDto);

    @Named("getUserId")
    static Integer getUserId(User user) {
        return user.getId();
    }

    @Named("getVhsId")
    static Integer getVhsId(Vhs vhs) {
        return vhs.getId();
    }

    @Named("getUser")
    static User getUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    @Named("getVhs")
    static Vhs getVhs(Integer vhsId) {
        Vhs vhs = new Vhs();
        vhs.setId(vhsId);
        return vhs;
    }
}
