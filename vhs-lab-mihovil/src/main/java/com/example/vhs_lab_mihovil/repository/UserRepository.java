package com.example.vhs_lab_mihovil.repository;

import com.example.vhs_lab_mihovil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
