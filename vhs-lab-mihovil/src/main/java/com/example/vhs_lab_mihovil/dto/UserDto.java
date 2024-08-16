package com.example.vhs_lab_mihovil.dto;

import lombok.Data;

@Data
public class UserDto {
    Integer id;
    String name;
    String surname;
    String username;
    String email;
    String phoneNumber;
}
