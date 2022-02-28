package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.services.UserServiceImpl;
import com.esliceu.forum.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl service;

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @GetMapping("/getprofile")
    public Map <String, Object> getProfileData(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);
        Cuenta cuenta = service.getUser(user);

        return cuenta.ObtainJson();
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    public Map <String, Object> updateUserProfile(@RequestHeader("Authorization") String token,
                                                  @RequestBody Map <String, Object> data) throws JsonProcessingException {

        token = token.replace("Bearer ", "");
        String user = jwtTokenUtil.getUsername(token);

        byte[] avatar = ((String) data.get("avatar")).getBytes();

        Cuenta cuenta = service.getUser(user);
        cuenta.setEmail((String) data.get("email"));
        cuenta.setName((String) data.get("name"));
        cuenta.setAvatar(avatar);

        service.updateUser(cuenta);

        Map <String, Object> result = new HashMap <>();
        result.put("user", cuenta.ObtainJson());
        result.put("token", token);

        return result;
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @PutMapping("/profile/password")
    public Map <String, Object> updateUserPassword(@RequestHeader("Authorization") String token, @RequestBody Map <String, Object> passwordData) throws JsonProcessingException {
        token = token.replace("Bearer ", "");

        String user = jwtTokenUtil.getUsername(token);

        String actualPassword = (String) passwordData.get("currentPassword");
        String newPassword = (String) passwordData.get("newPassword");

        service.updatePassword(user, actualPassword, newPassword);

        Cuenta cuenta = service.getUser(user);

        return cuenta.ObtainJson();

    }


}
