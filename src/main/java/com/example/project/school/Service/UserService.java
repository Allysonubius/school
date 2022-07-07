package com.example.project.school.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.school.Model.UserModel;
import com.example.project.school.Repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public UserModel userRegisteModel(UserModel userModel){
        userModel.setToken(tokenService.generateBearerToken(userModel));
    
        return this.userRepository.save(userModel);
    }
}
