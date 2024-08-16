package com.example.vhs_lab_mihovil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Price {
    @Id
    PriceType id;
    @NotNull
    BigDecimal amount;
}
