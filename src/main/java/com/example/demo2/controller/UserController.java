package com.example.demo2.controller;

import DTO.*;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.service.PayService;
import com.example.demo2.service.SaleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController{
    @Autowired
    PayService payService;
    @Autowired
    SaleService saleService;
    @PostMapping ("/metodoCompra")
    public SaleResponse saleResponse (@RequestParam Long idProduct){
     return    saleService.metodoCompra(idProduct);

    }
    @GetMapping ("/OnEstoque")
    public List<ProdutoEntity> onSale(){
     return saleService.onSale();
    }

    @PostMapping ("/carrinho")
    public SacolaResponse addOnCarrinho(@RequestBody SacolaRequest sacolaRequest){
       return saleService.addOnSacola( sacolaRequest );

    }
    @PostMapping ("/finishCarrinho")
    public SacolaResponse pagarCarrinho (@RequestParam Long idSacola){
       return saleService.finalizarCarrinho(idSacola);

    }
    @DeleteMapping ("/excluirItemOnSacola")
    public SacolaResponse excluirItemOnCarrinho (@RequestParam Long idSacola, @RequestParam Long idItem){
        return saleService.excluirItemSacola(idSacola, idItem);

    }
    @PostMapping ("/pagamento")
    public PayResponse pagamento(@RequestBody PayRequest payRequest){
       return payService.confirmationPay(payRequest);
    }


}
