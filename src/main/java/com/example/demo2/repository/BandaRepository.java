package com.example.demo2.repository;

import com.example.demo2.model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BandaRepository extends JpaRepository<Banda, Long> {
    boolean existsByNameBand(String nameBand);      // só confirma se existe
    Optional<Banda> findByNameBand(String nameBand); // traz o objeto completo
}
