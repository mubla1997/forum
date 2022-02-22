package com.esliceu.forum.services;

import com.esliceu.forum.DTO.RegisterRequest;
import com.esliceu.forum.configuration.MyConfiguration;
import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.repositories.CuentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    CuentaRepo cuentaRepo;

    @Override
    public UserDetails findByUsername(String email) {
        try {
            Cuenta cuenta = cuentaRepo.findByEmail(email);
            return new User(cuenta.getEmail(), MyConfiguration.passwordEncoder().encode(cuenta.getPassword()), new ArrayList <>());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDetails loadByUsername(String username)throws UsernameNotFoundException {
        Cuenta cuenta = cuentaRepo.findByEmail(username);
        return new User(cuenta.getEmail(), cuenta.getPassword(), new ArrayList <>());
    }

    @Override
    public Cuenta getUser(String email) {
        return cuentaRepo.findByEmail(email);
    }

    @Override
    public void createUser(RegisterRequest request) {
           Cuenta cuenta = new Cuenta();
           cuenta.setName(request.getName());
           cuenta.setEmail(request.getEmail());
           cuenta.setPassword(MyConfiguration.passwordEncoder().encode(request.getPassword()));
           cuenta.setRole(request.getRole());
           cuentaRepo.save(cuenta);

       }

    @Override
    public void updateUser(Cuenta cuenta) {
        cuentaRepo.save(cuenta);
    }

    @Override
    public void updatePassword(String email, String currentPassword, String newPassword) {
        Cuenta cuenta = cuentaRepo.findByEmail(email);
        newPassword = MyConfiguration.passwordEncoder().encode(newPassword);

        if(MyConfiguration.passwordEncoder().matches(currentPassword, cuenta.getPassword())){
            cuenta.setPassword(newPassword);
            cuentaRepo.save(cuenta);
        }
    }

}
