package com.example.vhs_lab_mihovil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "app_user") // 'user' is a keyword in SQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String name;
    @NotNull
    String surname;
    @NotNull
    String username;
    @NotNull
    String email;
    String phoneNumber;
}
