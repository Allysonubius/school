package com.example.project.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.school.Model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, String>{
    
}
