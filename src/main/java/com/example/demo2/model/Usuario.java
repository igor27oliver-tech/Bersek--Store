package com.example.demo2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClient;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @OneToOne
    @JoinColumn (name = "id_sacola")
   private Sacola sacola;
    
}
