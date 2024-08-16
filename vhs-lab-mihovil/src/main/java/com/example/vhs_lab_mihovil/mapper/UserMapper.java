package com.example.vhs_lab_mihovil.mapper;

import com.example.vhs_lab_mihovil.dto.UserDto;
import com.example.vhs_lab_mihovil.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDto> {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
}
