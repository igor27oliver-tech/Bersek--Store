//package com.example.demo2.config;
//
//import com.example.demo2.model.Banda;
//import com.example.demo2.model.ProdutoEntity;
//import com.example.demo2.repository.BandaRepository;
//import com.example.demo2.repository.EstoqueRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DataSeeder {
//
//    @Bean
//    public CommandLineRunner seedDatabase(BandaRepository bandaRepository, EstoqueRepository estoqueRepository) {
//        return args -> {
//            if (bandaRepository.count() == 0) {
//                Banda metallica = new Banda();
//                metallica.setNameBand("Metallica");
//                metallica.setCountry("US");
//                metallica.setYearStart("1981");
//                bandaRepository.save(metallica);
//
//                Banda blackSabbath = new Banda();
//                blackSabbath.setNameBand("Black Sabbath");
//                blackSabbath.setCountry("GB");
//                blackSabbath.setYearStart("1968-09");
//                bandaRepository.save(blackSabbath);
//
//                ProdutoEntity masterOfPuppets = new ProdutoEntity();
//                masterOfPuppets.setNameProduct("Master of Puppets");
//                masterOfPuppets.setCategoriaProduto("CD");
//                masterOfPuppets.setQuantidadeProduto(5);
//                masterOfPuppets.setDescricaoProduto("perfeitas condições");
//                masterOfPuppets.setAno(1986);
//                masterOfPuppets.setBanda(metallica);
//                masterOfPuppets.setValorProduto(23);
//                estoqueRepository.save(masterOfPuppets);
//
//                ProdutoEntity paranoid = new ProdutoEntity();
//                paranoid.setNameProduct("Paranoid");
//                paranoid.setCategoriaProduto("Vinil");
//                paranoid.setQuantidadeProduto(3);
//                paranoid.setDescricaoProduto("edição remasterizada");
//                paranoid.setAno(1970);
//                paranoid.setBanda(blackSabbath);
//               paranoid.setValorProduto(33);
//                estoqueRepository.save(paranoid);
//            }
//        };
//    }
//}