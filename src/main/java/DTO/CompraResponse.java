package DTO;

import com.example.demo2.model.ProdutoEntity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraResponse {
    private String codigo;
    private String descricao;
    @ManyToOne
    private ProdutoEntity produto;
}
