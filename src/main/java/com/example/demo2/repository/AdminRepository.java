package com.example.demo2.repository;

import com.example.demo2.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmailAdmin(String emailAdmin);
}
