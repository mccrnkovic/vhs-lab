package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VhsRepository extends JpaRepository<Vhs, Integer> {

    @Modifying
    @Query("UPDATE Vhs SET available = :available WHERE id = :vhsId")
    int updateAvailability(@Param("vhsId") Integer vhsId, @Param("available") Boolean available);
}
