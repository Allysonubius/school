package com.example.project.school.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.util.Objects.isNull;
import com.example.project.school.Entity.UserAuthEntity;
import com.example.project.school.Entity.UserRegisterEntity;
import com.example.project.school.Model.UserModel;
import com.example.project.school.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    @Autowired
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private static Gson gson = new Gson();

    @PostMapping(path="/registerUser")
    public ResponseEntity<Object> userRegisResponseEntity (@RequestBody UserRegisterEntity userRegisterEntity){
        JsonObject messageJson = new JsonObject();
        String message;
        try {
            UserModel userModel = this.userService.userRegisteModel(userRegisterEntity.toUserRegister());
            JsonElement jsonElement = gson.toJsonTree(userModel);
            messageJson.add("User", jsonElement);
            
            //return ResponseEntity<Object>(UserAuthEntity.toDTO(userModel, "Bearer "), HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(messageJson.toString());
        } catch (Exception e) {
            LOG.error("ERROR", e);
            message = e.getMessage() + (isNull(e.getCause()) ? "" : e.getCause().getMessage());
            messageJson.add("error",new JsonPrimitive(message));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageJson.toString());
        }
    }
}
