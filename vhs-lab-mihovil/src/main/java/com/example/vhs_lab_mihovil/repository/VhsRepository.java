package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VhsRepository extends JpaRepository<Vhs, Integer> {
}
