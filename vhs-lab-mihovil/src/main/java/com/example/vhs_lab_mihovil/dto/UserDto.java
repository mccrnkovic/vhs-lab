package com.example.vhs_lab_mihovil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    Integer id;
    @NotNull(message = "{notNullMsg}")
    String name;
    @NotNull(message = "{notNullMsg}")
    String surname;
    @NotNull(message = "{notNullMsg}")
    String username;
    @NotNull(message = "{notNullMsg}")
    String email;
    String phoneNumber;
}
