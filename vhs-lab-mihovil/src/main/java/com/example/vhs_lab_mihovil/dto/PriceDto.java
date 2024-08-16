package com.example.vhs_lab_mihovil.dto;

import com.example.vhs_lab_mihovil.model.PriceType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDto {
    @NotNull(message = "{price.type.notNull}")
    PriceType id;
    @NotNull(message = "{price.amount.notNull}")
    BigDecimal amount;
}
