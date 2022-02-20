package com.esliceu.forum.controllers;

import com.esliceu.forum.DTO.LoginRequest;
import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.services.MainServiceImpl;
import com.esliceu.forum.utils.JwtTokenUtil;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MainServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity <String> getLogin(@RequestBody LoginRequest request){
        // Falta el try para las excepciones

            User user = authenticate(request);
            Cuenta cuenta = service.getUser(request.getEmail());
            String tokenUtils = jwtTokenUtil.generateAccessToken(user);

            JSONObject jsonObject = new JSONObject();
            jsonObject.appendField("user", cuenta);
            jsonObject.appendField("token",tokenUtils);

            return ResponseEntity.ok()
                    .body(jsonObject.toJSONString());
    }

    private User authenticate(LoginRequest request) {
        Authentication autenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),request.getPassword()
                        )
                );
        return (User) autenticate.getPrincipal();
    }
}
