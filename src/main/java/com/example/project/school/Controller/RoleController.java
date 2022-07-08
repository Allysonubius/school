package com.example.project.school.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.util.Objects.isNull;
import com.example.project.school.Entity.RoleEntity;
import com.example.project.school.Model.RoleModel;
import com.example.project.school.Service.RoleService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@RestController
@RequestMapping(path="/api")
public class RoleController {
    
    @Autowired
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private RoleService roleService;
    
    private static Gson gson = new Gson();

    @PostMapping(path="/registerRole")
    public ResponseEntity<Object> roleRegisResponseEntity (@RequestBody RoleEntity roleEntity){
        JsonObject messageJson = new JsonObject();
        String message;
        try {
            RoleModel userModel = this.roleService.createNewRole(roleEntity.toRoleRegister());
            JsonElement jsonElement = gson.toJsonTree(userModel);
            messageJson.add("Roles", jsonElement);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(messageJson.toString());
        } catch (Exception e) {
            LOG.error("ERROR", e);
            message = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            messageJson.add("error",new JsonPrimitive(message));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageJson.toString());
        }
    }

}
