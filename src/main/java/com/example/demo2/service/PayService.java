package com.example.demo2.service;

import DTO.PayRequest;
import DTO.PayResponse;
import DTO.SacolaResponse;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.model.Sacola;
import com.example.demo2.repository.SacolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class PayService {
    @Autowired
    SacolaRepository sacolaRepository;
    @Autowired
    SaleService saleService;


public SacolaResponse searchedSacola (long idSacola){
  Sacola sacola = sacolaRepository.findById(idSacola).orElseThrow(() -> new RuntimeException("Erro ao carregar Sacola"));
  List<ProdutoEntity> listProdutos = new ArrayList<>(sacola.getListaProduto());
  int valorTotal = 0;
  for (ProdutoEntity produto : listProdutos){
      valorTotal += produto.getValorProduto();
  }
  SacolaResponse sacolaResponse = new SacolaResponse();
  sacolaResponse.setValorTotal(valorTotal);
  sacolaResponse.setListaProduto(sacola.getListaProduto());
  sacolaResponse.setIdSacola(sacola.getIdSacola());
  return sacolaResponse;
}




    public PayResponse confirmationPay (PayRequest payRequest){
      SacolaResponse sacolaResponse = searchedSacola(payRequest.idPd());
      int valorPago = payRequest.valor();
      PayResponse payResponse = new PayResponse();
      if (valorPago == sacolaResponse.getValorTotal()){
          payResponse.setWayToPay(payRequest.wayToPay());
          payResponse.setValorpago(valorPago);
          payResponse.setSacolaResponse(sacolaResponse);
      }
      saleService.finalizarCarrinho(payRequest.idPd());
        return payResponse;

    }



}
