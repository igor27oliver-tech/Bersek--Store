package com.example.demo2.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken (long idClient,String role){

        return Jwts.builder().subject(idClient+"").claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSecretKey()).compact();

    }
    public Long extractIdClient(String token) {
        // usa a receita acima, termina com .getSubject()
        // mas repara: getSubject() retorna String, e você precisa de Long
        return Long.parseLong(Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject());



    }
    public String extractRole(String token) {
        return Jwts.parser().verifyWith( getSecretKey()).build().parseSignedClaims(token).getPayload().get("role",String.class);
    }

}
