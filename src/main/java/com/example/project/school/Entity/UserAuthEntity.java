package com.example.project.school.Entity;

import com.example.project.school.Model.RoleModel;
import com.example.project.school.Model.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAuthEntity{

    private String tipo;
    private String nome;
    private String email;
    private String token;
    private RoleModel role;

    public UserAuthEntity(String nome, String email, String token, String tipo,RoleModel role) {
        this.email = email;
        this.nome = nome;
        this.token = token;
        this.tipo = tipo;
        this.role = role;
    }

    public static UserAuthEntity toDTO(UserModel user,String tipo) {
        return new UserAuthEntity(user.getEmail(), user.getNome(), user.getToken(),tipo, user.getRole());
    }
    
}
