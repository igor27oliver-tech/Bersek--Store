package com.example.demo2.controller;

import DTO.*;
import com.example.demo2.model.Banda;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.service.AdmService;
import com.example.demo2.service.MusicBrainzService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdmController { // buscamos na porta 8080/admin/buscar

    private final MusicBrainzService musicBrainzService;
    private final AdmService admService;
    public AdmController(MusicBrainzService musicBrainzService ,AdmService admService) {
        this.musicBrainzService = musicBrainzService ;
        this.admService = admService;
    }


    @GetMapping("/buscar")
    public MusicBrainzResponse searchBand(@RequestParam String banda) {

        List<MusicBrainzResponse> result = musicBrainzService.filter(musicBrainzService.buscarBanda(banda));

        MusicBrainzResponse artist = result.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Artista não encontrado: " + banda));
        return artist;
    }
  @PostMapping ("/saveBand")
    public MusicBrainzResponse saveBand(@RequestParam String  banda) {

      return admService.saveBand ( musicBrainzService.buscarBanda(banda).stream().findFirst().orElseThrow());

  }

  @GetMapping ("/band")
public  List<Banda> listBand() {
       return admService.findAllBanda();
  }

@PostMapping ("/createProduct")
    public ValidationProductResponse createProduct(@RequestBody ProductRegister register) {
     return  admService.createProduct(register);

}
@GetMapping ("/onEstoq")
    public List <ProdutoEntity> onEstoq() {
        return admService.findAllProduct();
}

@PutMapping ("/changeNameProduct")
    public ValidationProductResponse changeNameProduct(@RequestParam long idPd , String newName) {
     return admService.editNameItem(idPd, newName);


}

    @DeleteMapping ("/deletItem")
    public ValidationProductResponse deleteItem(@RequestParam long idPd) {
       return admService.deletItem(idPd);
    }

    @PostMapping ("/createMusica")
    public MusicResponse createMusica(@RequestBody MusicRegister register) {
        return admService.createMusic(register);
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestParam String email, @RequestParam String senha) {
        return admService.login(email,senha); // troca "service" pelo nome real da variável injetada

    }

}