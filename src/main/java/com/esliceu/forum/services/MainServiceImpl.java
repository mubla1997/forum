package com.esliceu.forum.services;

import com.esliceu.forum.DTO.RegisterRequest;
import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.repositories.CuentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    CuentaRepo cuentaRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails findByUsername(String email) {
        try {
            Cuenta cuenta = cuentaRepo.findByEmail(email);
            return new User(cuenta.getEmail(), passwordEncoder.encode(cuenta.getPasswd()), new ArrayList <>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Cuenta getUser(String email) {
        return cuentaRepo.findByEmail(email);
    }

    @Override
    public void createUser(RegisterRequest request) {
        Cuenta cuenta = new Cuenta();
        cuenta.setEmail(request.getEmail());
        cuenta.setUsername(request.getUsername());
        cuenta.setPasswd(passwordEncoder.encode(request.getPasswd()));
        cuenta.setRol(request.getRol());
        cuentaRepo.save(cuenta);
    }
}
