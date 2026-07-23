package com.example.demo2.repository;

import com.example.demo2.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SaleRepository extends JpaRepository<Sale,Integer>
{

}
