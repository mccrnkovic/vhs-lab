package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Modifying
    @Query("DELETE FROM Rental WHERE id = :id")
    int deleteRentalById(@Param("id") Integer id);
}
