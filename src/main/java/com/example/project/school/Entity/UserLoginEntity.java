package com.example.project.school.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginEntity{

    private String email;
    private String senha;

    public void UserModel(String email, String senha){
        this.email = email;
        this.senha = email;
    }
    
}
