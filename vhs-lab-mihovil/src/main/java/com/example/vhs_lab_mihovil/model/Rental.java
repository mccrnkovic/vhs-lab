package com.example.vhs_lab_mihovil.model;

import jakarta.persistence.*;
import lombok.Data;

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
    LocalDateTime dueDate;
}
