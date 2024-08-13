package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VhsRepository extends JpaRepository<Vhs, Integer> {
    List<Vhs> findAll();
}
