package com.example.demo2.service;

import DTO.CreateNewUser;
import DTO.UserResponse;
import com.example.demo2.config.JwtService;
import com.example.demo2.model.Usuario;
import com.example.demo2.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

   public UserResponse createNewUser(CreateNewUser createNewUser) {
     Usuario user = new Usuario();
      user.setEmail(createNewUser.email());
      user.setPassword(passwordEncoder.encode(createNewUser.senha()));
       userRepository.save(user);
      UserResponse userResponse = new UserResponse();
      userResponse.setEmail(user.getEmail());
      userResponse.setIdCLient(user.getIdClient());

      return  userResponse;
   }
   public String login (String email, String password) {
       Usuario usuario =userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Senha ou usuario não encontrado"));
       boolean senhaCorreta = passwordEncoder.matches(password,usuario.getPassword());
       if (!senhaCorreta) {
           throw new RuntimeException("senha incorreta");
       }
       return jwtService.generateToken(usuario.getIdClient(),"USER");
   }
}
