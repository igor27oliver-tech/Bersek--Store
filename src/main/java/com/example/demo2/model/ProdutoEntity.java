package com.example.demo2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoEntity {
    // informacoes sera fornecida pelo banco externo o musicbrainz
    @Id //chave primaria
    //alto Generate.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    private String categoriaProduto;
    private int quantidadeProduto;
    private String descricaoProduto;
    private int ano;
    private String pictureProduct;
    private int valorProduto;
    @ManyToOne
    private Banda banda;

}