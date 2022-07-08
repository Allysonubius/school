package com.example.project.school.Entity;

import com.example.project.school.Model.RoleModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleEntity extends RoleModel{
    
    private String roleName;
    private String roleDescription;

    public RoleModel toRoleRegister(){
        return new RoleModel( getRoleName(),getRoleDescription());
    }
}
