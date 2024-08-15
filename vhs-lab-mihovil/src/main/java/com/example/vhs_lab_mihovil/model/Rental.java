package com.example.vhs_lab_mihovil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    User user;
    @ManyToOne
    Vhs vhs;
    @NotNull
    LocalDateTime startDate;
    LocalDateTime returnDate;
    @NotNull(message = "{rental.dueDate.notNull}")
    @Future(message = "{rental.dueDate.future}")
    LocalDateTime dueDate;
    BigDecimal rentAmount;
    BigDecimal feeAmount;
}
