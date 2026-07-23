package com.example.demo2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Musica {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
  private   long idMusica;

    private String titulo;
    private String mp3Url;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_product")
    private ProdutoEntity produto;
}
