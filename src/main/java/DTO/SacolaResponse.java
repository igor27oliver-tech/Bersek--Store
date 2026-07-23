package DTO;

import com.example.demo2.model.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SacolaResponse {
   private long idSacola;
   private List<ProdutoEntity> listaProduto;
   private int valorTotal;
}
