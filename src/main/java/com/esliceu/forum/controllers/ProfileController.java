package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.services.MainServiceImpl;
import com.esliceu.forum.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MainServiceImpl service;

    @GetMapping("/getprofile") //Obtener los datos de usuario
    public Map<String,String> getProfileData(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);
        Cuenta cuenta = service.getUser(user);

        Map <String,String> json = new HashMap<>(); //avatar,email,name,id
        json.put("avatar", cuenta.getAvatar());
        json.put("email", cuenta.getEmail());
        json.put("name",cuenta.getName());
        return json;
    }

    @PutMapping("/profile") // Actualizar el perfil (name, email,foto)
    public ResponseEntity<String> updateUserProfile(@RequestHeader("Authorization") String token, @RequestBody /*Map*/String data) throws JsonProcessingException {

        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);

        ObjectMapper mapper = new ObjectMapper();
        Cuenta cuentaUP = mapper.readValue(data,Cuenta.class); //Obtener los datos de actualizacion
        String avatar = cuentaUP.getAvatar();

        Cuenta cuenta = service.getUser(user);
        cuenta.setName(cuentaUP.getName());
        cuenta.setEmail(cuentaUP.getEmail());
        cuenta.setAvatar(avatar);

        service.updateUser(cuenta);

        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("user", cuenta);
        jsonObject.appendField("token", token);

        return ResponseEntity.ok().body(jsonObject.toJSONString());
    }

    @PutMapping("/profile/password") // Actualiza la contraseña
    public ResponseEntity<String> updateUserPassword(@RequestHeader("Authorization") String token, @RequestBody String data){
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);
        return null;

    }


}
