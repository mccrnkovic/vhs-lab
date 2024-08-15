package com.example.vhs_lab_mihovil.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RentalDto {
    Integer id;
    @NotNull(message = "{rental.userId.notNull}")
    Integer userId;
    @NotNull(message = "{rental.vhsId.notNull}")
    Integer vhsId;
    LocalDateTime startDate;
    LocalDateTime returnDate;
    @NotNull(message = "{rental.dueDate.notNull}")
    @Future(message = "{rental.dueDate.future}")
    LocalDateTime dueDate;
    BigDecimal rentAmount;
    BigDecimal feeAmount;

}
