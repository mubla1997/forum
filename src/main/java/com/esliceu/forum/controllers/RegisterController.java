package com.esliceu.forum.controllers;


import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.services.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public class RegisterController {

    @Autowired
    MainServiceImpl service;

    @PostMapping("/register")
    public Cuenta getRegister(@RequestParam String email, @RequestParam String username,
                              @RequestParam String passwd, @RequestParam String rol){

        HashMap<String,String> NuevoUsuario = new HashMap <>();
        NuevoUsuario.put("email",email);
        NuevoUsuario.put("username", username);
        NuevoUsuario.put("passwd",passwd);
        NuevoUsuario.put("rol", rol);

        return service.createUser(NuevoUsuario.get("email"),NuevoUsuario.get("username"),
                NuevoUsuario.get("passwd"),NuevoUsuario.get("rol"));
    }
}
