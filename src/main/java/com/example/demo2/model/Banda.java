package com.example.demo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="banda")
@Getter
@Setter
public class Banda {
  @Id
  @GeneratedValue
    private long idBanda;
    private String nameBand;
    private String country;
    private String descrption;
    private String yearStart;
   
    
    
    
}