package com.example.vhs_lab_mihovil.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalDto {
    Integer id;
    @NotNull(message = "userId must not be null")
    Integer userId;
    @NotNull(message = "vhsId must not be null")
    Integer vhsId;
    LocalDateTime dueDate;
}
