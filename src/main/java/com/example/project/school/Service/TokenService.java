package com.example.project.school.Service;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.example.project.school.Model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
    
    private static final String JWT_KEY = "SECRET_KEY";

    private static final long JWT_EXPIRATION_TIME = 1800000;

    public String generateBearerToken(UserModel userModel){
        return Jwts.builder()
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject("Teste JWT API")
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, JWT_KEY)
        .compact();
    }

    public Claims decodeBearerToken(String token){
        return Jwts.parser()
        .setSigningKey(JWT_KEY)
        .parseClaimsJws(token)
        .getBody();
    }
}
