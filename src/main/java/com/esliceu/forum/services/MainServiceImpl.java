package com.esliceu.forum.services;

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
    public UserDetails findByUsername(String username) {
        try {
            Cuenta cuenta = cuentaRepo.findByUsername(username);
            return new User(cuenta.getUsername(), passwordEncoder.encode(cuenta.getPassword()), new ArrayList <>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
