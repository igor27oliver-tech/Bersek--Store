package com.example.demo2.controller;

import DTO.MusicResponse;
import com.example.demo2.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RadioController {
    @Autowired
    MusicService musicService;

    @GetMapping ("/radio")
    public List<MusicResponse> radio(){
        return musicService.listarMusicas();
    }
    @DeleteMapping("/delletAllSongs")
    public void delet (){
        musicService.deletAllMusics();
    }




}
