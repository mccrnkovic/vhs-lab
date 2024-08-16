package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Price;
import com.example.vhs_lab_mihovil.model.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceType> {
}
