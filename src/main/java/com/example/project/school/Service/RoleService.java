package com.example.project.school.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.school.Model.RoleModel;
import com.example.project.school.Repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository repository){
        this.roleRepository = roleRepository;
    }

    public RoleModel createNewRole(RoleModel roleModel) throws Exception{
        return this.roleRepository.save(roleModel);
    }
}
