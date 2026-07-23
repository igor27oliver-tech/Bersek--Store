package com.example.demo2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table (name = "sales")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
       private String nameProduct ;
       private String descProduct ;
       private int qtdProduct ;
       @ManyToOne(fetch = FetchType.LAZY)
       private Banda banda;
}
