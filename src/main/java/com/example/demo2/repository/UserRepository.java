package com.example.demo2.repository;

import com.example.demo2.model.Usuario;
import jakarta.validation.constraints.Email;
import org.apache.catalina.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
