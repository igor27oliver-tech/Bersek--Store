package com.example.demo2.model;

import com.example.demo2.model.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "sacola")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSacola;

    @ManyToMany
    @JoinTable(
            name = "sacola_produto",
            joinColumns = @JoinColumn(name = "id_sacola"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<ProdutoEntity> listaProduto;

}