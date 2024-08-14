package com.example.vhs_lab_mihovil.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalDto {
    Integer id;
    Integer userId;
    Integer vhsId;
    LocalDateTime dueDate;
}
