package com.example.project.school.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.project.school.Model.UserModel;
import com.example.project.school.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;
    
    private static final String JWT_KEY = "SECRET_KEY";

    // Time expiration 1 hour
    private static final long JWT_EXPIRATION_TIME = 3600000;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateBearerToken(UserModel userModel){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject(userModel.getNome())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes())
        .compact();
    }

    public Claims decodeBearerToken(String token){
        return Jwts.parser()
        .setSigningKey(JWT_KEY.getBytes())
        .parseClaimsJws(token)
        .getBody();
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userEntity = this.userRepository.findById(email).get();

        if(userEntity != null){
            return new org.springframework.security.core.userdetails.User(
                    userEntity.getEmail(),
                    userEntity.getSenha(),
                    getAuthority(userEntity)
            );
        }else {
            throw new UsernameNotFoundException("User not found with username" + email);
        }
    }  

    private Set getAuthority(UserModel user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        return authorities;
    }
}
