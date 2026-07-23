package com.example.demo2.service;

import DTO.*;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.model.Sacola;
import com.example.demo2.model.Sale;
import com.example.demo2.repository.EstoqueRepository;
import com.example.demo2.repository.SacolaRepository;
import com.example.demo2.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class SaleService {
                @Autowired SaleRepository saleRepository;
                @Autowired MusicBrainzService musicBrainzService;
                @Autowired AdmService admService;
                @Autowired EstoqueRepository estoqueRepository;
                @Autowired
                SacolaRepository sacolaepository;


    // metodo comprar

    public SaleResponse metodoCompra (long idProduto){
        ProdutoEntity pdentity = estoqueRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + idProduto));
        if (pdentity.getQuantidadeProduto() <=0){
            throw new RuntimeException("Produto sem estoque disponível: " + pdentity.getNameProduct());

        }
        Sale sale = new Sale();
        sale.setNameProduct(pdentity.getNameProduct());
        sale.setBanda(pdentity.getBanda());
        sale.setDescProduct(pdentity.getDescricaoProduto());
        sale.setQtdProduct(pdentity.getQuantidadeProduto());
        saleRepository.save(sale);
        pdentity.setQuantidadeProduto(pdentity.getQuantidadeProduto() - 1);
        estoqueRepository.save(pdentity);



        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setProduto(pdentity);
        return saleResponse;

    }
    //Disponiveis para compra
    public List<ProdutoEntity> onSale() {
        return   admService.findAllProduct();
    }
    // sacola (Carrinho)

    public SacolaResponse addOnSacola(SacolaRequest request) {

        List<ProdutoEntity> produtos = estoqueRepository.findAllById(request.idsProdutos());

        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto válido encontrado para os IDs informados.");
        }

        Sacola sacola = new Sacola();
        sacola.setListaProduto(produtos);
        sacolaepository.save(sacola);

        SacolaResponse sacolaResponse = new SacolaResponse();
        sacolaResponse.setListaProduto(sacola.getListaProduto());
        sacolaResponse.setIdSacola(sacola.getIdSacola());
        int valorTotal = 0;
        for(ProdutoEntity sacola1 : sacola.getListaProduto()){
            valorTotal += sacola1.getValorProduto();
            sacolaResponse.setValorTotal(valorTotal);
        }
        sacolaResponse.setValorTotal(valorTotal);


        return sacolaResponse;
    }
    @Transactional
    public SacolaResponse excluirItemSacola(Long idSacola, Long idPd) {
        Sacola sacola = sacolaepository.findById(idSacola)
                .orElseThrow(() -> new RuntimeException("Sacola não encontrada: " + idSacola));

        List<ProdutoEntity> listOnSacola = new ArrayList<>(sacola.getListaProduto());
        listOnSacola.removeIf(produto -> produto.getIdProduct().equals(idPd));

        sacola.setListaProduto(listOnSacola);
        sacolaepository.save(sacola);

        SacolaResponse sacolaResponse = new SacolaResponse();
        sacolaResponse.setIdSacola(sacola.getIdSacola());
        sacolaResponse.setListaProduto(sacola.getListaProduto());
       int valorTotal = 0;
       for (ProdutoEntity sacola1 : sacola.getListaProduto()) {
           valorTotal += sacola1.getValorProduto();

       }
       sacolaResponse.setValorTotal(valorTotal);

        return sacolaResponse;
    }

    public SacolaResponse finalizarCarrinho(long idSacola) {
        Sacola sacola = sacolaepository.findById(idSacola).orElseThrow(() -> new RuntimeException("erro na sacola"));
        List<ProdutoEntity> produtos = new ArrayList<>(sacola.getListaProduto());
        SacolaResponse sacolaResponse = new SacolaResponse();
        sacolaResponse.setListaProduto(sacola.getListaProduto());
        sacolaResponse.setIdSacola(idSacola);



        int  valorTotal = 0;
        for (ProdutoEntity sacola1 : sacola.getListaProduto()) {
            valorTotal += sacola1.getValorProduto();
        } sacolaResponse.setValorTotal(valorTotal);

        sacolaepository.deleteById(sacola.getIdSacola());
        for (ProdutoEntity produto : produtos) {
            estoqueRepository.deleteById(produto.getIdProduct());
        }


//        estoqueRepository.deleteById(sacola.getIdSacola());


        return sacolaResponse;



    }

}
