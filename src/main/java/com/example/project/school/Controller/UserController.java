package com.example.project.school.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.school.Entity.UserAuthEntity;
import com.example.project.school.Entity.UserRegisterEntity;
import com.example.project.school.Model.UserModel;
import com.example.project.school.Service.UserService;

@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    //@Autowired
    //private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path="/registerUser")
    public ResponseEntity<UserAuthEntity> userRegisResponseEntity (@RequestBody UserRegisterEntity userRegisterEntity){
        UserModel userModel = this.userService.userRegisteModel(userRegisterEntity.toUserRegister());
        return new ResponseEntity<UserAuthEntity>(UserAuthEntity.toDTO(userModel, "Bearer "), HttpStatus.CREATED);
    }
}
