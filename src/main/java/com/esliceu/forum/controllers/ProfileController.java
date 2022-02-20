package com.esliceu.forum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("getprofile") //Obtener los datos de usuario
    public void getProfileData(){}

    @PutMapping("/profile") // Actualizar el perfil (name, email,foto)
    public void updateProfile(){}

    @PutMapping("/profile/password") // Actualiza la contraseña
    public void updatePasswd(){}


}
