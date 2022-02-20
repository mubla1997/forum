package com.esliceu.forum.services;

import com.esliceu.forum.DTO.RegisterRequest;
import com.esliceu.forum.models.Cuenta;
import org.springframework.security.core.userdetails.UserDetails;

public interface MainService {
    UserDetails findByUsername(String email);
    Cuenta getUser(String email);
    void createUser(RegisterRequest request);
}
