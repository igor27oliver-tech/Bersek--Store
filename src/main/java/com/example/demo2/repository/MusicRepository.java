package com.example.demo2.repository;

import com.example.demo2.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Musica, Long> {
}
