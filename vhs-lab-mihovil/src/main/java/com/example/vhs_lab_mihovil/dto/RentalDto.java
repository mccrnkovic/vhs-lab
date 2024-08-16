package com.example.vhs_lab_mihovil.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RentalDto {
    Integer id;
    @NotNull(message = "{rental.userId}{notNullMsg}")
    Integer userId;
    @NotNull(message = "{rental.vhsId}{notNullMsg}")
    Integer vhsId;
    LocalDateTime startDate;
    LocalDateTime returnDate;
    LocalDateTime dueDate;
    BigDecimal rentAmount;
    BigDecimal feeAmount;

}
