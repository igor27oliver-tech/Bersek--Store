package com.example.demo2.controller;


import DTO.*;
import com.example.demo2.model.Admin;
import com.example.demo2.model.ProdutoEntity;
import com.example.demo2.repository.AdminRepository;
import com.example.demo2.service.PayService;
import com.example.demo2.service.SaleService;
import com.example.demo2.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.beans.Encoder;
import java.util.List;

@RestController
public class UserController{
    @Autowired
    PayService payService;
    @Autowired
    SaleService saleService;
    @Autowired
    UserService userService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping ("/metodoCompra")
    public SaleResponse saleResponse (@RequestParam Long idProduct){
     return    saleService.metodoCompra(idProduct);

    }
    @GetMapping ("/OnEstoque")
    public List<ProdutoEntity> onSale(){
     return saleService.onSale();
    }

    @PostMapping ("/carrinho")
    public SacolaResponse addOnCarrinho(Authentication authentication, @RequestBody SacolaRequest sacolaRequest){
        Long idClient = (Long) authentication.getPrincipal();
       return saleService.addOnSacola(idClient, sacolaRequest );

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
    @PostMapping ("/cadastro")
    public UserResponse register (@RequestBody CreateNewUser  createNewUser){
       return userService.createNewUser(createNewUser);

    }
    @PostMapping ("/login")
    public String login (@RequestParam String email, @RequestParam String senha){
        return userService.login(email,senha);
    }
    @PostMapping ("/createAdmin")
    public String createAdmin (@RequestParam String email,@RequestParam String senha){
        Admin admin = new Admin();
        admin.setEmailAdmin(email);
        admin.setPasswordAdmin(passwordEncoder.encode(senha));
        adminRepository.save(admin);
return "success " + admin.getEmailAdmin();

    }

}
