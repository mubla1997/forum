package com.esliceu.forum.controllers;

import com.esliceu.forum.DTO.PermisionsRequest;
import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.services.UserServiceImpl;
import com.esliceu.forum.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProfileController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl service;

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @GetMapping("/getprofile")
    public Map<String,Object> getProfileData(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);
        Cuenta cuenta = service.getUser(user);

        return cuenta.ObtainJson();
    }
    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestHeader("Authorization") String token, @RequestBody /*Map*/String data) throws JsonProcessingException {

        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);

        ObjectMapper mapper = new ObjectMapper();
        Cuenta cuentaUP = mapper.readValue(data,Cuenta.class);
        String avatar = cuentaUP.getAvatar();

        Cuenta cuenta = service.getUser(user);
        cuenta.setName(cuentaUP.getName());
        cuenta.setEmail(cuentaUP.getEmail());
        cuenta.setAvatar(avatar);

        service.updateUser(cuenta);

        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("user", cuenta.ObtainJson());
        jsonObject.appendField("token", token);

        return ResponseEntity.ok().body(jsonObject.toJSONString());
    }
    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @PutMapping("/profile/password")
    public Map<String, Object> updateUserPassword(@RequestHeader("Authorization") String token, @RequestBody String data) throws JsonProcessingException {
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);

        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> passwords = mapper.readValue(data,Map.class);
        String actualPassword = passwords.get("currentPassword");
        String newPassword = passwords.get("newPassword");

        service.updatePassword(user,actualPassword,newPassword);

        Cuenta cuenta = service.getUser(user);

        return cuenta.ObtainJson();

    }


}
