package com.example.vhs_lab_mihovil.dto;

import lombok.Data;

@Data
public class VhsDto {
    Integer id;
    String title;
    String publisher;
    Integer year;
    Boolean available;
}
