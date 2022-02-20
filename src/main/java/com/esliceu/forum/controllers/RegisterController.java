package com.esliceu.forum.controllers;


import com.esliceu.forum.DTO.RegisterRequest;
import com.esliceu.forum.services.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    MainServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity <String>  getRegister(@RequestBody RegisterRequest request){

        service.createUser(request);

        return ResponseEntity.ok().body("ok");
    }
}
