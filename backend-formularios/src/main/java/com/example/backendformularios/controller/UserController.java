package com.example.backendformularios.controller;

import com.example.backendformularios.model.*;
import com.example.backendformularios.service.FormService;
import com.example.backendformularios.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveForm(@RequestBody UserRequest userRequest){
        try{
            User saved = userService.saveUser(userRequest);
            return new ResponseEntity<>(new UserResponse(saved.getId(),"User saved successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new UserResponse(null,  "Error saving user " + userRequest.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        try{
            List<User> all = userService.findAll();
            if (ObjectUtils.isEmpty(all)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(all, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
