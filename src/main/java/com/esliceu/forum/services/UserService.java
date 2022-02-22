package com.esliceu.forum.services;

import com.esliceu.forum.DTO.RegisterRequest;
import com.esliceu.forum.models.Cuenta;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails findByUsername(String email);
    UserDetails loadByUsername(String username);
    Cuenta getUser(String email);
    void createUser(RegisterRequest request);
    void updateUser(Cuenta cuenta);
    void updatePassword(String email,String currentPassword,String newPassword);
}
