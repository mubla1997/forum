package com.esliceu.forum.services;

import com.esliceu.forum.models.Cuenta;
import org.springframework.security.core.userdetails.UserDetails;

public interface MainService {
    UserDetails findByUsername(String username);
    Cuenta createUser(String email, String username, String passwd, String rol);
}
