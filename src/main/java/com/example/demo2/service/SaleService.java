package com.example.demo2.service;

import DTO.*;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.model.Sacola;
import com.example.demo2.model.Sale;
import com.example.demo2.model.Usuario;
import com.example.demo2.repository.EstoqueRepository;
import com.example.demo2.repository.SacolaRepository;
import com.example.demo2.repository.SaleRepository;
import com.example.demo2.repository.UserRepository;
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
                @Autowired
                 UserRepository userRepository;


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

    public SacolaResponse addOnSacola(Long idCliente , SacolaRequest sacolaRequest) {
        Usuario usuario = userRepository.findById(idCliente).orElseThrow(()-> new RuntimeException("usuario não encontrado"));
        List<ProdutoEntity> produtos = estoqueRepository.findAllById(sacolaRequest.idsProdutos());
        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto válido encontrado para os IDs informados.");
        }


        if (usuario.getSacola() == null){
            Sacola sacola = new Sacola();
            sacola.setListaProduto(produtos);
            usuario.setSacola(sacola);
            sacolaepository.save(sacola);
            userRepository.save(usuario);

        }
        else {
           usuario.getSacola().getListaProduto().addAll(produtos);
           sacolaepository.save(usuario.getSacola());
        }
        SacolaResponse sacolaResponse = new SacolaResponse();
        sacolaResponse.setIdSacola(usuario.getSacola().getIdSacola());
        sacolaResponse.setListaProduto(usuario.getSacola().getListaProduto());
        int valorTotal = 0;
            for (ProdutoEntity produto1 : sacolaResponse.getListaProduto()) {
                valorTotal += produto1.getValorProduto();


            }

        sacolaResponse.setValorTotal(valorTotal);

    return  sacolaResponse;




    }
    @Transactional
    public SacolaResponse excluirItemSacola(Long idCliente,long idPd) {
       Usuario usuario = userRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("usuario não encontrado"));

        if (usuario.getSacola() == null) {
            throw new RuntimeException("Usuário não possui sacola");
        }

       Sacola sacola = sacolaepository.findById(usuario.getSacola().getIdSacola()).orElseThrow(() -> new RuntimeException("sacola inexistente"));

       List<ProdutoEntity> listOnSacola = new ArrayList<>(sacola.getListaProduto());
       listOnSacola.removeIf(produtoEntity -> produtoEntity.getIdProduct().equals(idPd));
       sacola.setListaProduto(listOnSacola);
       sacolaepository.save(sacola);
        SacolaResponse sacolaResponse = new SacolaResponse();
        sacolaResponse.setIdSacola(usuario.getSacola().getIdSacola());
        sacolaResponse.setListaProduto(sacola.getListaProduto());
        int valorTotal = 0;
        for (ProdutoEntity produto1 : sacolaResponse.getListaProduto()) {
            valorTotal += produto1.getValorProduto();


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
