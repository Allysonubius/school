package com.example.project.school.Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "USERS")
public class UserModel implements Serializable{
    
    @Serial
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID", nullable = false, updatable = false, unique = true,columnDefinition = "BINARY(36)")
    @Type(type = "uuid-char")
    private UUID id;

    
    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "SENHA")
    private String senha;
    
    @Column(name = "TOKEN")
    private String token;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private RoleModel role;

    public UserModel(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UserModel(UUID id, String nome, String email, String senha){
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
   
}
