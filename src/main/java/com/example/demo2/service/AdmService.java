package com.example.demo2.service;

import DTO.*;
import com.example.demo2.config.JwtService;
import com.example.demo2.model.Admin;
import com.example.demo2.model.Banda;
import com.example.demo2.model.Musica;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AdmService {
    @Autowired
    private BandaRepository bandaRepository;
    @Autowired
    private MusicBrainzService musicBrainzService;
    @Autowired
    EstoqueRepository estoqueRepository;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public MusicBrainzResponse saveBand(MusicBrainzResponse response) {
        Banda banda = new Banda();
        banda.setNameBand(response.getName());
        banda.setDescrption(null);
        banda.setCountry(response.getCountry());
        banda.setYearStart(response.getLifeSpan().getBegin());
        bandaRepository.save(banda);
        return response;
    }

    public ValidationProductResponse createProduct(ProductRegister request) {

        Banda banda = bandaRepository.findByNameBand(request.banda())
                .orElseThrow(() -> new RuntimeException("Banda não cadastrada no sistema: " + request.banda()));

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNameProduct(request.nomeProduto());
        produtoEntity.setCategoriaProduto(request.tipoProduto());
        produtoEntity.setDescricaoProduto(request.description());
        produtoEntity.setQuantidadeProduto(request.quantidade());
        produtoEntity.setAno(request.ano());
        produtoEntity.setBanda(banda); // associa a banda já persistida
        produtoEntity.setPictureProduct(request.productPicture());
        produtoEntity.setValorProduto(request.valorProduto());
        estoqueRepository.save(produtoEntity);
        ValidationProductResponse response = new ValidationProductResponse();
        response.setNomeProduto(request.nomeProduto());
        response.setNomeBanda(banda.getNameBand());
        response.setCountry(banda.getCountry());
        response.setYearStart(banda.getYearStart());
        response.setDescricao(produtoEntity.getDescricaoProduto());
        response.setQuantidade(produtoEntity.getQuantidadeProduto());
        response.setAno(produtoEntity.getAno());
        response.setProductPicture(produtoEntity.getPictureProduct());
        response.setValorProduto(produtoEntity.getValorProduto());
        //teste ID
        response.setIdProduto(produtoEntity.getIdProduct());


        return response;

    }

    public List<Banda> findAllBanda() {
        return bandaRepository.findAll();
    }

    public List<ProdutoEntity> findAllProduct() {
        return estoqueRepository.findAll();

    }

    public ValidationProductResponse deletItem (long idItem){
        ProdutoEntity produto = estoqueRepository.findById(idItem).orElse(null);
        ValidationProductResponse response = new ValidationProductResponse();
        response.setNomeProduto(produto.getDescricaoProduto());
        response.setNomeBanda(produto.getBanda().getNameBand());
        response.setAno(produto.getAno());
        estoqueRepository.deleteById(idItem);
        return response;

    }
    public ValidationProductResponse editNameItem (long idItem, String newNameItem) {
        ProdutoEntity produto = estoqueRepository.findById(idItem).orElse(null);
        produto.setNameProduct(newNameItem);
        ValidationProductResponse  response = new ValidationProductResponse();
        response.setNomeProduto(newNameItem);
        response.setIdProduto(idItem);
        response.setAno(produto.getAno());
        estoqueRepository.save(produto);
        return response;


    }
    public MusicResponse createMusic (MusicRegister register){
        MusicResponse musicResponse = new MusicResponse();
        Musica musica = new Musica();
        musica.setTitulo(register.titulo());
        musica.setMp3Url(register.mp3Url());
        musica.setProduto(estoqueRepository.findById(register.idProduto()).orElse(null));
        musicRepository.save(musica);
        musicResponse.setIdMusica(musica.getIdMusica());
        musicResponse.setTitulo(musica.getTitulo());
        musicResponse.setMp3Url(musica.getMp3Url());
        musicResponse.setIdProduto(musica.getIdMusica());
        return musicResponse;





    }

    public String login (String email, String password){
      Admin admin  = adminRepository.findByEmailAdmin(email).orElseThrow(() -> new RuntimeException("Senha ou usuario não encontrado"));
        boolean senhaCorret = passwordEncoder.matches(password,admin.getPasswordAdmin());
        if (!senhaCorret){
            throw new RuntimeException("senha incorreta");
        }
        return jwtService.generateToken(admin.getId(),"ADMIN");

    }


}