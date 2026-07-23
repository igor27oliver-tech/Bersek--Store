package com.example.demo2.service;

import DTO.MusicResponse;
import com.example.demo2.model.Musica;
import com.example.demo2.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public List <MusicResponse> listarMusicas (){ //falta termina o metodo
      Musica musica = new Musica();
      List<Musica> musicas = new ArrayList<>(musicRepository.findAll());

      List <MusicResponse> listaMusica = new ArrayList<>();
     for( Musica musica1 : musicas){
         MusicResponse musicResponse = new MusicResponse();
         musicResponse.setNomeProduto(musica1.getProduto().getNameProduct());
         musicResponse.setMp3Url(musica1.getMp3Url());
         musicResponse.setPictureProduto(musica1.getProduto().getPictureProduct());
         musicResponse.setIdMusica( musica1.getIdMusica());
         musicResponse.setTitulo(musica1.getTitulo());




         listaMusica.add(musicResponse);


     }
        return listaMusica;

    }
    public void deletAllMusics(){
        musicRepository.deleteAll();

    }


}
