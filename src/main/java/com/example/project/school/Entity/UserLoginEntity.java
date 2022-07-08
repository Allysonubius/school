package com.example.project.school.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginEntity{

    private String email;
    private String senha;
    private RoleEntity role;

    public void UserModel(String email, String senha,RoleEntity role){
        this.email = email;
        this.senha = email;
        this.role = role;
    }
    
}
