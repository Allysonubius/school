package com.example.project.school.Entity;

import com.example.project.school.Model.UserModel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserRegisterEntity extends UserModel{

    private String nome;
    private String email;
    private String senha;

    public UserRegisterEntity(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UserModel toUserRegister(){
        return new UserModel(getNome(), getEmail(), getSenha());
    }
}
