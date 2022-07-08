package com.example.project.school.Service;

import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.project.school.Entity.UserLoginEntity;
import com.example.project.school.Exception.ExistingEmailException;
import com.example.project.school.Exception.ExpiredTokenException;
import com.example.project.school.Exception.InvalidLoginException;
import com.example.project.school.Exception.InvalidTokenException;
import com.example.project.school.Model.UserModel;
import com.example.project.school.Repository.UserRepository;
import io.jsonwebtoken.Claims;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public UserModel userRegisteModel(UserModel userModel) throws Exception{
        Optional<UserModel> optional = this.userRepository.findByEmail(userModel.getEmail());
        if(!optional.isPresent() && userModel.getEmail().length() > 0){
            userModel.setToken(tokenService.generateBearerToken(userModel));
            return this.userRepository.save(userModel);
        }else{
            throw new Exception("Ja existe o email registrado.");
        }
    }

    public UserModel authenticateUser(UserLoginEntity userLoginEntity, String token) throws Exception{
        UserModel userModel = this.userRepository.findByEmail(userLoginEntity.getEmail()).orElseThrow(ExistingEmailException::new);
        if(userLoginEntity.getSenha().equals(userLoginEntity.getSenha()) && !token.isEmpty() && token != null && validadeToken(token)){
            return userModel;
        }else{
            throw new InvalidLoginException();
        }
    }

    public boolean validadeToken(String token){
        try{
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = this.tokenService.decodeBearerToken(tokenTratado);
            if(claims != null){
                String username = claims.getSubject();
                Date expirationDate = claims.getExpiration();
                Date now = new Date(System.currentTimeMillis());
                if(username != null && expirationDate != null && now.before(expirationDate)){
                    return true;
                }
            }
            return false;
        }catch (ExpiredTokenException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
