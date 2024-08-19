package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("DELETE FROM app_user WHERE id = :id")
    int deleteUserById(@Param("id") Integer id);
}
