package com.example.vhs_lab_mihovil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VhsDto {
    Integer id;
    @NotNull
    String title;
    @NotNull
    String publisher;
    Integer year;
    @NotNull(message = "{vhs.available}{notNull}")
    Boolean available;
}
