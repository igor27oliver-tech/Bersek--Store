

package com.example.demo2.service;

import DTO.LifeSpan;
import DTO.MusicBrainzArtistSearchResponse;
import DTO.MusicBrainzResponse;
import DTO.ValidationProductResponse;
import com.example.demo2.model.Banda;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.repository.BandaRepository;
import com.example.demo2.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MusicBrainzService {
    @Autowired
    private BandaRepository bandaRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;


    public List<MusicBrainzResponse> buscarBanda(String nomeBanda) {
        //ferramente do Spring que nos auxilia a fazer requisoes em APIS externas exerna no nosso projeto
        RestTemplate restTemplate = new RestTemplate();
        // Stirng url = contem onde vamos buscar e como.
        String url =
                "https://musicbrainz.org/ws/2/artist/?query="
                        + nomeBanda
                        + "&fmt=json";
        // resposta é gurdada no response
        MusicBrainzArtistSearchResponse response = restTemplate.getForObject(url, MusicBrainzArtistSearchResponse.class);

        //"Aqui estamos dizendo: faça a requisição pra essa url, pegue o texto que voltar como resposta, e construa um objeto do tipo indicado a partir desse texto. Os parâmetros são: a url (de onde vem o texto) e o tipo de objeto que queremos que seja construído."
        return response.getArtists();
    }

    public List<MusicBrainzResponse> filter(List<MusicBrainzResponse> artists) {

        return artists.stream().limit(1).toList();


    }

    public MusicBrainzResponse confirmationName(String nome) {

        Banda bd = bandaRepository.findByNameBand(nome)
                .orElseThrow(() -> new RuntimeException("Banda não encontrada: " + nome));

        LifeSpan lifeSpan1 = new LifeSpan();
        lifeSpan1.setBegin(bd.getYearStart());

        MusicBrainzResponse response = new MusicBrainzResponse();
        response.setName(bd.getNameBand());
        response.setCountry(bd.getCountry());
        response.setLifeSpan(lifeSpan1);

        return response;



    }






}














