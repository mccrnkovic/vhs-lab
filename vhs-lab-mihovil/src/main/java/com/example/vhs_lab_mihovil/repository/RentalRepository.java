package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
