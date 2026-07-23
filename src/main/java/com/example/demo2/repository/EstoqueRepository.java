package com.example.demo2.repository;

import com.example.demo2.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueRepository extends JpaRepository <ProdutoEntity,Long>{

}
