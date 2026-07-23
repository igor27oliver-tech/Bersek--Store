package DTO;

import com.example.demo2.model.Banda;
import com.example.demo2.model.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProductResponse {

    private Long idProduto;
    private String nomeProduto;
    private String nomeBanda;
    private String country;
    private String yearStart; // ano de início da banda
    private String descricao;
    private int quantidade;
    private int ano; //
    private String productPicture;
    private int valorProduto;
}
